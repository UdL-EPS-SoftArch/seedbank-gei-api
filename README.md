# Seed Bank GEI API

Seed Bank project API based on a template for a Spring Boot project including Spring REST, HATEOAS, JPA, etc. Additional details: [HELP.md](HELP.md)

[![Open Issues](https://img.shields.io/github/issues-raw/UdL-EPS-SoftArch/seedbank-gei-api?logo=github)](https://github.com/orgs/UdL-EPS-SoftArch/projects/16)
[![CI/CD](https://github.com/UdL-EPS-SoftArch/seedbank-gei-api/actions/workflows/ci-cd.yml/badge.svg)](https://github.com/UdL-EPS-SoftArch/seedbank-gei-api/actions)
[![CucumberReports: UdL-EPS-SoftArch](https://messages.cucumber.io/api/report-collections/faed8ca5-e474-4a1a-a72a-b8e2a2cd69f0/badge)](https://reports.cucumber.io/report-collections/faed8ca5-e474-4a1a-a72a-b8e2a2cd69f0)
[![Deployment status](https://img.shields.io/uptimerobot/status/m792713336-92bf9993ec46d798b1dd89c0)](https://seedbank-gei-api.fly.dev)

## Vision

**For** ... **who** want to ...
**the project** ... **is an** ...
**that** allows ...
**Unlike** other ...

## Features per Stakeholder

| USER            | ADMIN             | DONOR           | PROPAGATOR       |
|-----------------|-------------------|-----------------|------------------|
| Register        | Approve User      | List Requests   | List Donations   |
| Login           | Check Donation    | Search Requests | Search Donations |
| Logout          | Alert Seed Needed | Donate          | Take             |
| List Takes      |                   | Match           |                  |
| Search Takes    |                   |                 |                  |
| View Profile    |                   |                 |                  |
| Post Blog Entry |                   |                 |                  |
| Edit Profile    |                   |                 |                  |

### Features' Details

* **List** / **Search**: when listing or searching, show first older, nearer seeds.
* **Take** is allowed if the ration between amount taken and that donated is not too negative. Ratio definition to be discussed...
* **Match**: **Donations** might be matched to existing **Requests** based on ratio, date and location, though **Donors** can override the match and pick their preferred **Request**.
* **View Profile**: it is possible to view public **Profiles**, though **Admins** can view all and users always their own.

## Entities Model

![EntityModelsDiagram](https://www.plantuml.com/plantuml/svg/5SqnhW8X40RW_ftYUG2OtcejjjF4PEC1M1YNYJ0G-2TUNws-ybicme3LydSIZSKIsoZ_6UZpJ3J2sn-2RBjD7h4fME5Zg_H8LxVUDxUr9ouZPfhCQG_wSWUB2rse1b5Q5GBXUMk9Y--_Ps5DNpy0?v0)

