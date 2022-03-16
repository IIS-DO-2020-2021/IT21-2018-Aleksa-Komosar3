package command;

public interface Command {
	//nas interfejs za dalje za modifikaciju da se izvrsi i ponisti
	void execute();
	void unexecute();
}
