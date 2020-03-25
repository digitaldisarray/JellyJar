package jelly.modules;

public abstract class Module {
	private boolean enabled = false;
	private String name;
	
	
	public Module() {
		this.name = this.getClass().getSimpleName();
	}
	
	// Run every single program loop
	public void onLoop() {
		
	}
	
	// Run every time we loop through an entity
	public void onEntityLoop(long entity) {
		
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void toggle() {
		enabled = !enabled;
	}
	
	public String getName() {
		return name;
	}
}
