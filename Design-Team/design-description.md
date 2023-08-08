# CS6300 Deliverable 1 - Design Description

## Overview:

The UML design depicts a single-user job offer comparison app that facilitates the evaluation of job offers by considering multiple factors such as salary, cost of living, different locations, and so on. This app is designed to allow users to effortlessly enter and modify job details and offers, compare them against their current job details, and customize the comparison settings as desired.


## The UML diagram consists of the following classes: 

MainMenu: The app starts with a main menu that allows the user to perform various actions, including entering or editing current job details, entering job offers, adjusting comparison settings, or comparing job offers.

User: This class represents the entry point of the app. It provides methods to display the main menu, job details entry/editing interface, job offer entry interface, comparison settings adjustment interface, job offer comparison interface, and the comparison results table.

JobDetails: This class represents the details of a job, including attributes such as title, company, location, cost of living, yearly salary, yearly bonus, leave, maternity/paternity leave, and life insurance. The user can enter or edit these details for their current job or job offers.

JobOfferCatalog: This class represents a job offer and inherits the attributes from the JobDetails class. Users can enter and save multiple job offers, which can later be compared with the current job details.

ComparisonSettings: This class allows the user to adjust the comparison settings by assigning integer weights to factors such as yearly salary, yearly bonus, leave, maternity/paternity leave, and life insurance. If no weights are assigned, all factors are considered equal.

JobComparison: This class handles the comparison of job offers. It computes the score for each job offer based on the provided formula, using the weighted sum of various factors. The job offers are ranked from best to worst, including the current job (if present).


## The following attributes and operations exist in the classes: 

### Class: MainMenu
#### Attributes: 
- user: User          
- jobOfferCatalog: JobOfferCatalog       
- jobComparison: JobComparison
- comparisonSettings: ComparisonSettings
#### Operations:
- enterCurrentJobDetails()                         
- enterJobOffers(): void               
- adjustComparisonSettings(): void   
- compareJobOffers(): void              

### Class: JobDetails
#### Attributes: 
- title: String
- company: String
- location: Location
- costOfLivingIndex: Float
- yearlySalary: Float
- signingBonus: Float
- leave: Integer
- maternityPaternityLeave: Integer
- lifeInsurance: Integer
#### Operations:
- editDetails()
- save()
- computeAdjustedSalary()
- computeAdjustedBonus()
- computeScore()

### Class: JobOfferCatalog
#### Attributes:
- currentJobOffer: JobOffer
- jobOffers: List<JobOffer>
#### Operations:
- save(jobOffer: Job)
- returnToMainMenu()
- compare(jobOffer: JobOffer, currentJob: CurrentJob)
- addAnother()
- cancel()

### Class: ComparisonSettings
#### Attributes:
- salaryWeight: Integer
- bonusWeight: Integer
- leaveWeight: Integer
- maternityPaternityLeaveWeight: Integer
- lifeInsuranceWeight: Integer

#### Operations: 
- editWeights()

### Class: JobComparison
#### Attributes: 
- rankedJobs: List<JobOffers> 

#### Operations:
- rank(jobs: List<JobOffers>, comparisonSettings: ComparisonSettings): List<JobOffers>
- displayRankedJobs(jobs: List<JobOffers>)
- returnToMainMenu()
- compare(jobA: JobDetails, jobB: JobDetails)
- costOfLivingAdjuster(amount, costofLivingIndex)


## The relationships between the classes are as follows:

The User class interacts with all other classes to display the appropriate interfaces and retrieve user inputs.

The JobDetails class is associated with the User class for entering and editing job details.

The JobOffer class is associated with the User class for entering and saving job offers.

The ComparisonSettings class is associated with the User class for adjusting the comparison settings.

The JobComparison class is associated with the User class to display the job offer comparison results.


## The operation mechanism of the job offer comparison app is as follows:

1.	When the app is started, the user is presented with the main menu: This is achieved by calling the start() method in the User class, which then calls the displayMainMenu() method.
2.	Enter or edit current job details: The JobDetails class has a CurrentJob object of the Job class. It also has a method editDetails(), which allows the user to enter or edit the details of the current job.
3.	Enter job offers: The MainMenu class has a method enterJobOffers(), which allows users to enter the details of job offers.
4.	Adjust the comparison settings: This is accomplished by the editWeights() method in the ComparisonSettings class. The MainMenu class interacts with this class to change the weights based on user input.
5.	Compare job offers: This is accomplished by the compareJobOffers() method in the MainMenu class. It uses the JobComparison class and its compare() method to compare job offers.
6.	When ranking jobs: This requirement is realized in the computeScore() method in the JobDetails class. The computeScore() method uses the weights from the ComparisonSettings class to compute the score.
7.	The user interface must be intuitive and responsive: This requirement is not directly reflected in the design as it pertains to the actual implementation of the UserInterface class. However, this class is designed to handle the interaction with the user, which includes ensuring that the interface is intuitive and responsive.
8.  The design assumes a single system running the app, without the need for communication or saving between devices: This is not shown in our UML design as it is part of the implementation.  