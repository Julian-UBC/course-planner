My Personal Project
-------------------

Course Planning
===============

I came up with an idea about course planning. This application will mainly help users to plan the courses they
should take to fulfill their requirements to graduate from university (for now only works for UBC). For now, people
that will use my application will be *UBC Computer Science Students*. Hopefully in the future, all students in UBC 
or even students from any universities can use my application. The reason I want to make **Course Planning** 
application for my project is because I want to use this application to help me plan what courses to take, so
I can fulfill the requirements to graduate.

User Stories
------------
- As a user, I want to be able to add a course to my course list 
- As a user, I want to be able to view the list of courses on my course list
- As a user, I want to be able to remove a course from my course list
- As a user, I want to be able to view the details of a specific course on my course list
- As a user, I want to be able to save my worklists to a file
- As a user, I want to be able to load my worklists from a file

Phase 4: Task 2
---------------
The option I have chosen to implement is:
- Test and design a class in your model package that is robust.  You must have at least one method that throws a checked 
exception.  You must have one test for the case where the exception is expected and another where the exception 
is not expected.
  
Classes & methods that have robust design:
- Course :
    - the constructor
    - changeCourseName
    - changeCourseCredit
- CourseList :
    - the constructor
    - removeRecentCourse
    - setCourseListName
    
Phase 4: Task 3
---------------
If I had more time to work on the project, I would definitely do more refactoring to improve my design. Although I have 
already done some refactoring, I think there is still some more refactoring that I can do. I think some of my classes 
still have low cohesion and/or high coupling. I have done a lot of refactoring in CoursePlanningPage class, but I feel 
like there is still some more refactoring that I can do to improve my design. I probably need to extract some more
classes to handle different responsibilities in CoursePlanningPage to improve cohesion and coupling.
