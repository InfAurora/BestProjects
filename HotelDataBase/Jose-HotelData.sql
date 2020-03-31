-- insert data using my name first, then add everything else in the example data
-- must delete Jeremiah Pendergrass (reservation, guest, roomReservation)

use hoteldb;

insert into guest (guestId, firstName, lastName, address, city, state, zip, phoneNumber)
values(1, 'Jose', 'Alba', '100 tiger Ln', 'Kenny town', 'MN', '55055', '1111111111');

insert into guest (firstName, lastName, address, city, state, zip, phoneNumber)
values('Mack', 'Simmer', '379 Old Shore Street', 'Council Bluffs', 'IA', '51501', '2915530508'),
('Bettyann ', 'Seery', '750 Wintergreen Dr.', 'Wasilla', 'Ak', '99654', '4682779632' ),
('Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', '78552', '3084940198'),
('Karie', 'Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', '08096', '4940198'),
('Aurore', 'Lipton', '762 Wild Rose Street', 'Saginaw', 'MI', '48601', '3775070974'),
('Zachery', 'Leuchtefeld', '7 Polar Dr.', 'Arvada', 'CO', '80003', '8144852615'),
('Jeremiah', 'Pendergrass', '70 Oakwood St.', 'Zion', 'IL', '60099', '2794910960'),
('Walter', 'Holaway', '7556 Arrowhead St.', 'Cumberland', 'RI', '02864', '4463966785'),
('Wilfred', 'Vise', '77 West Surrey Stree', 'Oswego', 'NY', '13126', '8347271001'),
('Maritza', 'Tilton', '939 Linda Rd.', 'Burke', 'VA', '22015', '4463516860'),
('Joleen', 'Tison', '87 Queen St.', 'Drexel Hill', 'PA', '19026', '2318932755');

insert into reservation (reservationId, startDate, endDate,
adultCount, childrenCount, totalRoomCost, guestId)
values(1, '2023/2/2', '2023/2/4', 1, 0, 299.98, 2),
(2, '2023/2/5', '2023/2/10', 2, 1, 999.95, 3),
(3, '2023/2/22', '2023/2/24', 2, 0, 349.98, 4),
(4, '2023/3/6', '2023/3/7', 2, 2, 199.99, 5),
(5, '2023/3/17', '2023/3/20', 1, 1, 524.97, 1),
(6, '2023/3/18', '2023/3/23', 3, 0, 924.95, 6),
(7, '2023/3/29', '2023/3/31', 2, 2, 349.98, 7),
(8, '2023/3/31', '2023/4/5', 2, 0, 874.95, 8),
(9, '2023/4/9', '2023/4/13', 1, 0, 799.96, 9),
(10, '2023/4/23', '2023/4/24', 1, 1, 174.99, 10),
(11, '2023/5/30', '2023/6/2', 2, 4, 1199.97, 11),
(12, '2023/6/10', '2023/6/14', 2, 0, 599.96, 12),
(13, '2023/6/10', '2023/6/14', 1, 0, 599.96, 12),
(14, '2023/6/17', '2023/6/18', 3, 0, 184.99, 6),
(15, '2023/6/28', '2023/7/2', 2, 0, 699.96, 1),
(16, '2023/7/13', '2023/7/14', 3, 1, 184.99, 9),
(17, '2023/7/18', '2023/7/21', 4, 2, 1259.97, 10),
(18, '2023/7/28', '2023/7/29', 2, 1, 199.99, 3),
(19, '2023/8/30', '2023/9/1', 1, 0, 349.98, 3),
(20, '2023/9/16', '2023/9/17', 2, 0, 149.99, 2),
(21, '2023/9/13', '2023/9/15', 2, 2, 399.98, 5),
(22, '2023/11/22', '2023/11/25', 2, 2, 1199.97, 4),
(23, '2023/11/22', '2023/11/25', 2, 0, 449.97, 2),
(24, '2023/11/22', '2023/11/25', 2, 2, 599.97, 2),
(25, '2023/12/24', '2023/12/28', 2, 0, 699.96, 11);

insert into Type (typeId, `name`, standardOccupancy, maxOccupancy, basePrice, extraPersonCost)
values (1, 'Single', 2, 2, 149.99, 0),
(2, 'Double', 2, 4, 174.99, 10),
(3, 'Suite', 3, 8, 399.99, 20);

insert into Amenity (amenityId, `name`, charge)
values(1, 'Microwave', 0),
(2, 'Refrigerator', 0),
(3, 'Oven', 0),
(4, 'Jacuzzi', 25);

insert into Room (roomId, roomNumber, typeId, adaId) -- take off amen
values(1, 201, 2, 0),
(2, 202, 2, 1),
(3, 203, 2, 0),
(4, 204, 2, 1),
(5, 301, 2, 0),
(6, 302, 2, 1),
(7, 303, 2, 0),
(8, 304, 2, 1);

insert into Room (roomNumber, typeId, adaId) -- take off amen
values (205, 1, 0),
(206, 1, 1),
(207, 1, 0),
(208, 1, 1),
(305, 1, 0),
(306, 1, 1),
(307, 1, 0),
(308, 1, 1),
(401, 3, 1),
(402, 3, 1);

insert into roomAmenity (roomId, amenityId) -- will get bigger
values(1,1),(1,4),(2,2),(3,1),(3,4),(4,2),(5,1),(5,4),(6,2),
(7,1),(7,4),(8,2),(9,1),(9,2),(9,4),(10,1),(10,2),(11,1),(11,2),(11,4),(12,1),(12,2),
(13,1),(13,2),(13,4),(14,1),(14,2),(15,1),(15,2),(15,4),(16,1),(16,2),
(17,1),(17,2),(17,3),(18,1),(18,2),(18,3);

insert into roomReservation(roomId, reservationId)
values(16,1),(3,2),(13,3),(1,4),(15,5),(6,6),(2,7),(8,8),
(5,9),(11,10),(17,11),(10,12),
(12,13),(8,14),(9,15),(4,16),(17,17),(7,18),(13,19),
(12,20),(3,21),(17,22),(10,23),(5,24),
(6,25);

delete from roomReservation
where reservationId = 8;

delete from reservation
where reservationId = 8;

delete from guest
where guestId = 8;

select * from amenity;
select * from guest;
select * from reservation;
select * from room;
select * from roomAmenity;
select * from roomReservation;
select * from type;