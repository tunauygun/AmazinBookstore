# Amazin Book Store - Group 11

## Project Description
Bookstore Owner can upload and edit Book information (ISBN, picture, description, author, publisher,...) and inventory. User can search for, and browse through, the books in the bookstore, sort/filter them based on the above information. User can then decide to purchase one or many books by putting them in the Shopping Cart and proceeding to Checkout. The purchase itself will obviously be simulated, but purchases cannot exceed the inventory. User can also view Book Recommendations based on past purchases. This is done by looking for users whose purchases are most similar (using Jaccard distance: Google it!), and then recommending books purchased by those similar users but that the current User hasn't yet purchased.

## Project UI Prototypes
The link below contains the latest version of the user interface prototypes of the application
https://docs.google.com/presentation/d/1X9QLdqQebS50-IBtIaBtbH3_m5YRN2V0/edit?usp=sharing&ouid=102518669893232841637&rtpof=true&sd=true

## URLs for the deployed application
#### Main homepage can access all the other pages when starting here
https://amazinbookstoreapp-e6arfpg3ezc9ezep.canadacentral-01.azurewebsites.net/amazinBookstore
http://localhost:8080/amazinBookstore
#### Access page to access admin view of books
https://amazinbookstoreapp-e6arfpg3ezc9ezep.canadacentral-01.azurewebsites.net/amazinBookstore/admin
http://localhost:8080/amazinBookstore/admin
#### Access page to access customer books in cart
https://amazinbookstoreapp-e6arfpg3ezc9ezep.canadacentral-01.azurewebsites.net/amazinBookstore/cart
http://localhost:8080/amazinBookstore/cart
#### Checkout page for purchasing books in the cart
https://amazinbookstoreapp-e6arfpg3ezc9ezep.canadacentral-01.azurewebsites.net/amazinBookstore/checkout
http://localhost:8080/amazinBookstore/checkout
#### Managing books as Admin
https://amazinbookstoreapp-e6arfpg3ezc9ezep.canadacentral-01.azurewebsites.net/amazinBookstore/admin/books
http://localhost:8080/amazinBookstore/admin/books
#### Viewing books as Customer
https://amazinbookstoreapp-e6arfpg3ezc9ezep.canadacentral-01.azurewebsites.net/amazinBookstore/customer/books
http://localhost:8080/amazinBookstore/customer/books
#### Viewing book recommendations as Customer
https://amazinbookstoreapp-e6arfpg3ezc9ezep.canadacentral-01.azurewebsites.net/amazinBookstore/customer/recommended_books
http://localhost:8080/amazinBookstore/customer/recommended_books

## Milestone 3 update:
- To help with the navigation within the app, added back buttons.
- Create and edit user and admin page is working.
- Sort can dynamically shows the change in database.
- The recommended books page now displays actuall book recommendations based on the Jaccard Distance of previous purchases.
- The admin book creation page now uses Webflux/Webclient to get the cover image URL of the book from an external API based on ISBN-13 number.

## Database Schema for Milestone 3
![testDb](https://github.com/user-attachments/assets/eeb5a12f-1515-429a-a54a-366d250b7eb4)
## UML diagram for Milestone 3
![image](https://github.com/user-attachments/assets/d2aca65f-6655-4f84-9b5a-0ebbfce03b83)

