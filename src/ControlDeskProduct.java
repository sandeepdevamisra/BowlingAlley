

import java.util.Vector;
import java.util.Iterator;
import java.util.HashSet;

public class ControlDeskProduct implements Cloneable {
	private Queue partyQueue;
	private ControlDeskSubscriber subscribers;

	public void setPartyQueue(Queue partyQueue) {
		this.partyQueue = partyQueue;
	}

	public void setSubscribers(ControlDeskSubscriber subscribers) {
		this.subscribers = subscribers;
	}

	/**
	* Creates a party from a Vector of nickNAmes and adds them to the wait queue.
	* @param partyNicks 	A Vector of NickNames
	*/
	public void addPartyQueue(Vector partyNicks) {
		Vector partyBowlers = new Vector();
		for (int i = 0; i < partyNicks.size(); i++) {
			Bowler newBowler = BowlerFile.registerPatron(((String) partyNicks.get(i)));
			partyBowlers.add(newBowler);
		}
		Party newParty = new Party(partyBowlers);
		partyQueue.add(newParty);
		subscribers.publish(new ControlDeskEvent(getPartyQueue()));
	}

	/**
	* Returns a Vector of party names to be displayed in the GUI representation of the wait queue.
	* @return  a Vector of Strings
	*/
	public Vector getPartyQueue() {
		Vector displayPartyQueue = new Vector();
		for (int i = 0; i < ((Vector) partyQueue.asVector()).size(); i++) {
			String nextParty = ((Bowler) ((Vector) ((Party) partyQueue.asVector().get(i)).getMembers()).get(0))
					.getNickName() + "'s Party";
			displayPartyQueue.addElement(nextParty);
		}
		return displayPartyQueue;
	}

	/**
	* Iterate through the available lanes and assign the parties in the wait queue if lanes are available.
	*/
	public void assignLane(HashSet thisLanes) {
		Iterator it = thisLanes.iterator();
		while (it.hasNext() && partyQueue.hasMoreElements()) {
			Lane curLane = (Lane) it.next();
			if (curLane.isPartyAssigned() == false) {
				System.out.println("ok... assigning this party");
				curLane.assignParty(((Party) partyQueue.next()));
			}
		}
		subscribers.publish(new ControlDeskEvent(getPartyQueue()));
	}

	public Object clone() throws CloneNotSupportedException {
		return (ControlDeskProduct) super.clone();
	}
}