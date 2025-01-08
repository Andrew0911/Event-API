# Event-API   
🌱 **SpringBoot Event Management API**

## Project Overview  
The Event Management API is designed to streamline the process of organizing, managing, and participating in events. Built with Spring Boot, this API provides a robust backend solution for event organizers, attendees, and venue managers, ensuring seamless functionality and adherence to specific business rules.

---

## Features  
### Business Rules Implemented:
1. **Event Rating Is Based on Reviews**: Events are rated dynamically based on attendees' reviews.
2. **Unique Ticket Per Attendee per Event**: Each attendee can book only one ticket per event.
3. **Reviews Can Be Updated and Deleted**: Attendees can manage their reviews for better feedback accuracy.
4. **Events Can Be Filtered by Date**: Users can search for events within specific date ranges.
5. **Only Romanian Phone Numbers Are Accepted**: Ticket booking is restricted to users with valid Romanian phone numbers.
6. **Events Have Limited Tickets**: Each event has a predefined maximum number of tickets available.
7. **Venue Capacity Limits Tickets**: The maximum ticket count is automatically restricted by the venue's capacity.
8. **Dynamic Ticket Pricing**: Ticket prices increase by **10 RON** for every 20% of the total stock sold.
9. **Review Period Restriction**: Attendees can leave reviews for an event only within **7 days** of its conclusion.
10. **Organizers and Venues Can Be Managed**: Organizers and venues can be added or deleted; deleting them removes all associated events.

---

## Minimum Viable Product (MVP)
For the MVP phase, the following core features have been implemented based on the business requirements:

1. **Event Creation and Management**:
   - Organizers can add new events while adhering to venue capacity restrictions.
   - Events are linked to specific venues and organizers.

2. **Ticket Booking**:
   - Users can book tickets for events.
   - Ticket availability is dynamically reduced, with pricing adjustments based on sales percentage.

3. **Event Search and Filtering**:
   - Users can search for events and apply date filters for better usability.

4. **Event Reviews**:
   - Attendees can leave reviews within the specified 7-day window and update or delete them as needed.

5. **Organizer and Venue Management**:
   - Organizers and venues cand be added or deleted, ensuring proper cascading deletions.

---

## Prerequisites  
### Tools and Libraries Required:
- Java 17 or later
- Maven (for dependency management)
- Spring Boot Framework
- MySQL (or your preferred database)
- Postman (for API testing)

---
