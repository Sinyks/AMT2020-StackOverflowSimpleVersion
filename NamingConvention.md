# Naming Convention

## Author

| Name              | Email                        |
| ----------------- | ---------------------------- |
| Sacha Perdrizat   | sacha.perdrizat@heig-vd.ch   |
| Guillaume Zaretti | guillaume.zaretti@heig-vd.ch |
| Maximilian Vogel  | maximilian.vogel@heig-vd.ch  |
| Alban Favre       | alban.favre@heig-vd.ch       |

## Goal

Provide a convention for naming Java class in our project that is agreed by all member of the group

## Class naming convention

A Class Servlet should be name as follow

```
{Action | Page targeted | Subject}[Optional Specifier][Servlet | DTO | ...].java
```

#### Example

```
HomePageServlet
LoginCommandServlet
LogoutCommandServlet
RegisterServlet
PersonDTO
```

