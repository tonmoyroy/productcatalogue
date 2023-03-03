#### Requirements elicitation strategy: ####
* Brainstorming, Requirement Document Analysis, Discuss with stakeholders, Prototyping and gather feedbacks


#### Development strategy: ####
* Divide task in epics and create jira tickets based on the minimum testable unit (MTU)
* Assign them to the team.


#### Deployment strategy: ####
* As we already have old version, we can deploy the new version alongside and perform similar tests on both.
* Testing will be quite hard but with help of extra resources can be distributed.


#### Rollout plan: ####
* Need to understand the expected behaviour for the new releases, accordingly perform automated test and manual testing in differnt environments.
* Communicating with team about those behaviours and perform actions if required.
* After rolling out keep monitoring the new releases.


#### Migration plan: ####
* Identify the data format in the NoSQLDB and figure out how can it be converted into the new format.
Prepare the new model accordingly.
* Import all data into the new system using batch process
* Also keep the old data backup
* Assess data and follow up with further maintenance of data


#### Test strategy: ####
* Motivate developers for Test driven development.
* Use different levels of standard testing in the project i.e. unit, integration & regression test
* Schedule regression testing before a new release (functional/non-functional)
* Prioritize testing based of the important ones.
* Keep track of the test performance.



#### Communication plan with external parties: ####
* Identify current status and risk. Inform accordingly with the external parties.
