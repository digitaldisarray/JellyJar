/*
 *    Copyright 2016 Jonathan Beaudoin
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package jelly.offsets.netvars;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayDeque;

import com.github.jonatino.misc.Strings;

import jelly.offsets.OffsetManager;
import jelly.offsets.PatternScanner;
import jelly.offsets.netvars.impl.ClientClass;
import jelly.offsets.netvars.impl.RecvProp;
import jelly.offsets.netvars.impl.RecvTable;

/**
 * Created by Jonathan on 11/16/2015.
 */
public final class NetVars {

	private static final ArrayDeque<NetVar> netVars = new ArrayDeque<>(16_500);

	private static final ClientClass clientClass = new ClientClass();
	private static final RecvTable table = new RecvTable();
	private static final RecvProp prop = new RecvProp();

	public static void load() {
		int firstclass = PatternScanner.byPattern(OffsetManager.clientModule(), 0, 0, 0, "DT_TEWorldDecal");
		firstclass = PatternScanner.byPattern(OffsetManager.clientModule(), 0x2B, 0, PatternScanner.READ, firstclass);

		for (clientClass.setBase(firstclass); clientClass.readable(); clientClass.setBase(clientClass.next())) {
			table.setBase(clientClass.table());
			String tableName = table.tableName();
			if (tableName.length() > 0 && table.propCount() > 0) {
				scanTable(table, 0, tableName);
			}
		}
	}

	public static void dump() {
		// Start it
		try {
			Files.write(Paths.get("JavaNetVars.txt"), ("public static class NetVarOffsets {\n").getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String currentClass = "";
		for (NetVar netvar : netVars) {
			if (!currentClass.equals(netvar.className)) {

				try {
					// If it is not the first ever class
					if (!currentClass.equals("")) {
						// End the previous class declaration
						Files.write(Paths.get("JavaNetVars.txt"), ("	}\n").getBytes(), StandardOpenOption.APPEND);
					}

					// Start new class decalaration
					Files.write(Paths.get("JavaNetVars.txt"),
							("	public static class " + netvar.className + " {\n").getBytes(),
							StandardOpenOption.APPEND);
				} catch (IOException e) {
					e.printStackTrace();
				}

				// Set it to new current class
				currentClass = netvar.className;
			}

			try {
				Files.write(Paths.get("JavaNetVars.txt"),
						("		public static int " + netvar.varName + " = " + netvar.offset + ";\n").getBytes(),
						StandardOpenOption.APPEND);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// End it
		try {
			Files.write(Paths.get("JavaNetVars.txt"), ("	}").getBytes(), StandardOpenOption.APPEND);
			Files.write(Paths.get("JavaNetVars.txt"), ("}").getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		List<String> text = new ArrayList<>();
//		netVars.forEach(n -> text.add(n.toString()));
//		try {
//			Files.write(Paths.get("NetVars.txt"), text);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private static void scanTable(RecvTable table, int offset, String className) {
		for (int i = 0; i < table.propCount(); i++) {
			prop.setBase(table.propForId(i));
			prop.setOffset(offset);

			String propName = prop.name();
			int propOffset = prop.offset();

			if (Character.isDigit(propName.charAt(0))) {
				continue;
			}

			if (propOffset != 0x0) {
				netVars.add(new NetVar(className, propName, propOffset));
			}

			int child = prop.table();
			if (child == 0) {
				continue;
			}
			scanTable(new RecvTable().setBase(child), propOffset, className);
		}
	}

	public static int byName(String className, String varname) {
		for (NetVar var : netVars) {
			if (var.className.equals(className) && var.varName.equals(varname)) {
				return var.offset;
			}
		}
		throw new RuntimeException("NetVar [" + className + ", " + varname + "] not found!");
	}

	private static class NetVar {

		private final String className;
		private final String varName;
		private final int offset;

		private NetVar(String className, String varName, int offset) {
			this.className = className;
			this.varName = varName;
			this.offset = offset;
		}

		@Override
		public String toString() {
			return className + " " + varName + " = " + Strings.hex(offset);
		}

	}

}
