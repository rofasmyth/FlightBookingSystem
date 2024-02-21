package ie.lyit.flight;

import java.util.ArrayList;

public class Booking {
	private Flight outbound;
	private Flight inbound;
	private ArrayList<Passenger> passengers;
	private double totalPrice;
	private int bookingNo;
	
	private static int nextUniqueBookingNumber = 10000;
	
	// Constructor for Booking with only a mandatory inbound Flight.
	public Booking(Flight outbound, Passenger mandatoryPassenger) {
		this.passengers = new ArrayList<Passenger>();
		this.bookingNo = nextUniqueBookingNumber++;
		this.outbound = outbound;
		
		addPassenger(mandatoryPassenger); //ensure Booking has at least 1 passenger.
	}
	
	// Constructor for Booking with both inbound and outbound Flight.
	public Booking(Flight outbound, Flight inbound, Passenger mandatoryPassenger) {
		this.passengers = new ArrayList<Passenger>();
		this.bookingNo = nextUniqueBookingNumber++;
		this.outbound = outbound;
		this.inbound = inbound;
		
		addPassenger(mandatoryPassenger); //ensure Booking has at least 1 passenger.
	}
	
	// Method to add more passengers to the Booking.
    public void addPassenger(Passenger passenger) {
    	// Ensure between 1 and 9 passengers.
        if (passenger != null && passengers.size() < 9) {
            passengers.add(passenger);
        } else {
            // Throw error if more than 9 added.
            throw new IllegalStateException("Cannot add more than 9 passengers to the booking.");
        }
    }
	
    // Method to remove passengers from Booking.
    public void removePassenger(Passenger passengerToRemove) {
    	// Ensure passengers can only be removed if there are more than one.
        if (passengers.size() > 1) {
            passengers.remove(passengerToRemove);
        } else {
            // Throw error if user attempts to remove last passenger.
            throw new IllegalStateException("Cannot remove the last passenger from the booking.");
        }
    }
	
    // Method to print Booking details including: Passenger and Flight class details.
	public String toString() {
		// StringBuilder object to facilitate String creation.
	    StringBuilder result = new StringBuilder("Booking details:\n");

	    // Add outbound Flight Details
	    result.append("Outbound Flight: ").append(outbound).append("\n");
	    
	    // Add inbound Flight details if they apply.
	    if (inbound != null) {
	        result.append("Inbound Flight: ").append(inbound).append("\n");
	    }
	    
	    // Add a label for passenger info.
	    result.append("Passengers: ");
	    // A for-loop adds passengers from the passenger toString(), one at a time.
	    for (int i = 0; i < passengers.size(); i++) {
	        result.append(passengers.get(i));
	    }
	    // Spacing
	    result.append("\n========================\n");
	    // Add the total price with the calcPrice() method.
	    result.append("Total Price: ").append(calcPrice());
	    
	    // Call to the StringBuilder toString() method.
	    return result.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		Booking bObject;
		if (obj instanceof Booking)
		   bObject = (Booking)obj;
		else
		   return false;
		   
	    return(this.bookingNo==bObject.bookingNo);
	}
	
	// Method returns True if a target passenger is in the passenger ArrayList.
	public boolean findPassenger(Passenger targetPassenger) {
		return passengers.contains(targetPassenger);
	}
	
	// Calculate the price by multiplying number of passengers by the individual
	// Flight price(s).
	public double calcPrice() {
		double totalPrice = 0;

        for (Passenger passenger : passengers) {
            double price = 0;
 
            price += outbound.getPrice();
            
            // Add the price of the inbound flight if applies.
            if (inbound != null) {
                price += inbound.getPrice();
            }

            // Multiply by the number of passengers.
            price *= getNumPassengers();

            // Add the individual passenger price to the total.
            totalPrice += price;
        }
        return totalPrice;
	}
	
	//--Mutator Methods---------------------------------------------------------//

	public Flight getOutbound() {
		return outbound;
	}
	
	// Error checking doesn't allow an outbound fight to be null.
	public void setOutbound(Flight outbound) {
        if (outbound == null) {
            throw new IllegalArgumentException("Outbound flight cannot be null");
        }
        this.outbound = outbound;
    }
	
	public Flight getInbound() {
		return inbound;
	}
	
	public void setInbound(Flight inbound) {
		this.inbound = inbound;
	}
	

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}
	
	public int getNumPassengers() {
		return passengers.size();
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public int getBookingNo() {
		return bookingNo;
	}
	
}
