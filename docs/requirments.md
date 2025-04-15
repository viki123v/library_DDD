## Librairy 

A public library allows patrons to place books on hold at its various library branches. Available books can be placed on hold only by one patron at any given point in time.
Books are either circulating or restricted, and can have retrieval or usage fees.5. 
A regular patron is limited to five holds at any given moment, while a researcher patron is allowed an unlimited number of holds.
An open-ended book hold is active until the patron checks out the book, at which time it is completed. A closed-ended book hold that is not completed within a fixed number of days after it was requested will expire.
This check is done at the beginning of a day by taking a look at daily sheet with expiring holds. Only a researcher patron can request an open-ended hold duration. 
Any patron with more than two overdue checkouts at a library branch will get a rejection if trying a hold at that same library branch. A book can be checked out for up to 60 days.
Check for overdue checkouts is done by taking a look at daily sheet with overdue checkouts. Patron interacts with his/her current holds, checkouts, etc. by taking a look at patron profile. 
Patron profile looks like a daily sheet, but the information there is limited to one patron and is not necessarily daily.
Currently a patron can see current holds (not canceled nor expired) and current checkouts (including overdue). 
Also, he/she is able to hold a book and cancel a hold.

How actually a patron knows which books are there to lend? Library has its catalogue of books where books are added together with their specific .
A specific book instance of a book can be added only if there is book with matching ISBN already in the catalogue. 
Book must have non-empty title and price. At the time of adding an instance we decide whether it will be Circulating or Restricted. 
This enables us to have book with same ISBN as circulated and restricted at the same time (for instance, there is a book signed by the author that we want to keep as Restricted)

```mermaid
classDiagram
    class RestrictedBookCopy
    class CirculatingBookCopy
    class Lend 
    class Book 
    class ResearchPatron
    class OrdinaryPatron 
    class LibBranch 
    class OpenHold 
    class ClosedHold

    class Hold 
    class Patron 
    
    Patron <|-- ResearchPatron
    Patron <|-- OrdinaryPatron
    Hold <|-- OpenHold
    Hold <|-- ClosedHold
    
    
    CirculatingBookCopy -- Lend
    CirculatingBookCopy -- Hold
    ResearchPatron -- OpenHold
    OrdinaryPatron "1" -- "0..5" ClosedHold
    OrdinaryPatron "1" --  "*" Lend 
    ResearchPatron "1" -- "*" Lend 
    
```

### Ubiquotes language 
- Book: book instance 
- Hold: a request, not a lend. That's why it sais in the text that the request will expire after a fix time when talking about a close ended hold 

## Advertisment 