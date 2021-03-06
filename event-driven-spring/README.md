Event Driven Applications with Spring Boot
This projects tries to capture various options you have when dealing with Event Driven Spring Boot applications. The follwing Spring Technologies are being used:

Spring Boot
Spring Cloud Stream Rabbit
Spring Data JPA
These examples contain various different ways to model and deal with events:

Complete aggregates / entities in the events
REST Resource URLs in events
Partial parsing / handling of events in consumers
Events as Atom Feeds
Prerequisites
You need to have Docker installed
How to run and install the example
In the root directory you need to

Compile everything with ./mvnw package
Start everything up with docker-compose up --build
Running on Kubernetes
Mind the KubernetesSetup.md file in the kubernetes directory

URLs and Ports
Each of the modules is it's own Spring Boot Application which can be accessed as follows:

Name	Application / Enpoint Type	URL
Application Process	9000	http://localhost:9000
Credit Application	9001	http://localhost:9001/credit-application
Customer	9002	http://localhost:9002/customer and http://localhost:9002/customer/feed
Scoring	9003	No UI
CreditDecision	9004	http://localhost:9004/credit-decision and http://localhost:9004/credit-decision/feed
Messaging Infrastructure & Domain Events
Public Events
CreditApplicationNumberGeneratedEvent
Source: application-process

Persisted in source: no

Consumers:

credit-application
credit-decision
Topic: CreditApplicationNumberGeneratedTopic

CreditApplicationEnteredEvent
Source: credit-application

Persisted in source: yes in its own Table via JPA

Consumers:

application-process
credit-decision
Topic: CreditApplicationEnteredTopic

CustomerCreatedEvent
Source: customer

Persisted in source: no

Consumers:

application-process
credit-decision
Topic: CustomerCreatedTopic

ScoringPositiveEvent
Source: scoring

Persisted in source: no

Consumers:

application-process
credit-decision
Topic: ScoringPositiveTopic

ScoringNegativeEvent
Source: scoring

Persisted in source: no

Consumers:

application-process
credit-decision
Topic: ScoringNegativeTopic

ApplicationDeclinedEvent
Source: credit-decision

Persisted in source: not as an event

Consumers:

application-process
Topic: ApplicationDeclinedTopic

Internal Events
Credit-Application
CreditDetailsEnteredEvent
FinancialSituationEnteredEvent
Both events are stored Source: credit-application Storage: Own Table via JPA

Feeds
Customer Feed
Url: http://localhost:9002/customer/feed

Contains URLs to Customer Resources

Credit Decision Feed
Url: http://localhost:9004/credit-decision/feed

Contains Application Numbers that have been confirmed

Event Types being used
This demo shows various types of event types: Events with all the data, Events with Resource Urls and "Events" as Feeds

Events with all the data
Especially the CreditApplicationEnteredEvent falls into this category: it contains all of the data for the credit application such as the financial situation and the details of the actual credit. By consuming this event you will not need additional roundtrips to upstream systems

Other events that fall into this category are:

ApplicationNumberGeneratedEvent
ScoringNegativeEvent
ScoringPositiveEvent
ApplicationDeclinedEvent
Idea of Bounded Context:
Please take a close look at how the CreditApplicationEnteredEvent is being reflected in the scoring application. Yes, we take in all the payload from the broker but the public model of the event has a clear focus on the scoring context's view on the data.
