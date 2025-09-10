#  Venue Manager Java AOOP Project

## Overview

This is a console based Java database application meant to simulate the database of a venue manager. It is designed to manage the data of the venue, staff, events, tickets and artists. 
The application includes logical access to CRUD operations alongside thoughtful queries or automated processes, meant to simulate the database interface for an internal venue application dedicated to an employee or manager.

## Features

Demonstrates key AOOP features, such as including encapsulation through private and protected classes, access methods, singleton patterns, inheritance, service classes and the use of multiple collection types.
The project implements a relational database using JDBC, with optimized data reading. Another feature is the audit log, which writes in a CSV file everytime an action is executed, along with a timestamp.

## Menu

### Venue Managment
+ View events by date
+ View Staff
+ Update venue details


### Events Management
+ View all upcoming events (ordered by date)
+ View a specific event
+ Add a new event

### For each event:
+ View artists
+ Sell tickets (automatic, per section)
+ Update event details
+ Delete event

### Artist Management (per event)
+ Add a new artist
+ Edit an artist’s details
+ Remove an artist

## Staff Management
+ View all staff
+ Add a new employee
+ Edit staff/manager details (with different menus for staff vs managers)
+ Promote/demote between staff and manager
+ Remove an employee

![Database Scheme](https://github.com/imnofuxkingfun/AOOP_VenueManager/blob/main/AOOP_VenueManager_Diagram.png)
