package ie.lyit.testers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ie.lyit.flight.Booking;
import ie.lyit.flight.CreditCard;
import ie.lyit.flight.Date;
import ie.lyit.flight.Flight;
import ie.lyit.flight.Name;
import ie.lyit.flight.Passenger;
import ie.lyit.flight.Time;

public class BookingTest {
	// Declare variables for test.
	private Booking b1;
	private Booking b2;
	private Flight AE0748;
	private Flight AE0749;
	private Name patrick;
	private Name brigid;
	private Name anthony;
	private Name francis;
	private Passenger p1;
	private Passenger p2;
	private Passenger p3;
	private Passenger p4;
	private CreditCard patCC;
	private CreditCard bridCC;
	private CreditCard tonyCC;
	private CreditCard franCC;


	@Before
	public void setUp() throws Exception {
		// Instantiate objects needed to create a Booking object.
		AE0748 = new Flight("AE0748", "Dublin", "Rome", new Date(24, 12, 2023), new Time(10, 30), 48.98);
		
		AE0749 = new Flight("AE0749", "Rome", "Dublin", new Date(30, 12, 2023), new Time(15, 15), 56.50);
		
		patrick = new Name("Mr.", "Saint", "Patrick");
		patCC = new CreditCard(1234567812345678L, new Date(31, 12, 2027), 111);
		
		brigid = new Name("Ms.", "Saint", "Brigid");
		bridCC = new CreditCard(1234567812345679L, new Date(31, 12, 2026), 222);
		
		anthony = new Name("Mr.", "Anthony", "DePaglia");
		tonyCC = new CreditCard(1234567812345680L, new Date(31, 12, 2025), 333);
		
		francis = new Name("Mr.", "Francis", "deAsissi");
		franCC = new CreditCard(1234567812345681L, new Date(31, 12, 2024), 444);
		
		p1 = new Passenger(patrick, "0852571618", "irish@gmail.com", 0, false, patCC);
		
		p2 = new Passenger(brigid, "0852571619", "pagan@gmail.com", 1, true, bridCC);
		
		p3 = new Passenger(anthony, "0852571620", "lostandfound@gmail.com", 2, false, tonyCC);
		
		p4 = new Passenger(francis, "0852571621", "animallover@gmail.com", 1, true, franCC);
		
		b1 = new Booking(AE0748, p3);
		
		b2 = new Booking(AE0748, AE0749, p1);
		
	}
	
	// Test Booking constructor with inbound and outbound flights.
	@Test
	public void testBookingFlightFlight() {
		
		assertNotNull(b2.getPassengers()); // passengers list should not be null
        assertEquals(AE0748, b2.getOutbound()); // check outbound flight
        assertEquals(AE0749, b2.getInbound()); // check inbound flight
        assertTrue(!(b2.getPassengers().isEmpty() )); // passengers list should not be empty 
	}
	
	// Test addPassenger()
	@Test
	 public void testAddPassenger() {
		
        // Add the passenger to the booking
        b1.addPassenger(p2);

        // Assert that the passenger is in the passengers list
        assertTrue(b1.getPassengers().contains(p2));
    }
		
	// Test removePassenger ()
	@Test
	public void testRemovePassenger() {
		  
		b1.addPassenger(p2);
	 	// Ensure that there are 2 passengers.
	 	assertEquals(2, b1.getPassengers().size());
	
	 	// Remove 1 passenger.
	 	b1.removePassenger(p3);
	
	 	// Check that the passenger was removed.
	 	assertEquals(1, b1.getPassengers().size());
	 	assertFalse(b1.getPassengers().contains(p3));
	 	assertTrue(b1.getPassengers().contains(p2));
	 }
	
	// Test error checking for removing the last passenger.
	@Test(expected = IllegalStateException.class)
	public void testRemoveLastPassenger() {
		// Remove the last passenger, which should throw an exception.
		b1.removePassenger(p2);
		//b1.removePassenger(p1);
	}
	
	// Test setOutbound()
	@Test 
    public void testSetOutbound() {

        // Set the outbound flight.
        b1.setOutbound(AE0749);

        // Check that the outbound flight is correct.
        assertEquals(AE0749, b1.getOutbound());
    }
	
	// Test the error checking for setOutbound().
	@Test(expected = IllegalArgumentException.class)
    public void testSetOutboundError() {

        // Try to set the outbound flight to null.
        b1.setOutbound(null);
    }

	// Test cascPrice().
    @Test
    public void testCalcPriceWithoutInbound() {
        // Set prices for the outbound flight and passengers
    	AE0748.setPrice(100.0);

        // Calculate the total price without an inbound flight
        double totalPrice = b1.calcPrice();

        // Check if the total price is calculated correctly       
        assertEquals(100.0, totalPrice, 0.01);
    }

}
