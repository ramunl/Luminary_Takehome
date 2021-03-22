
## Description

The project is implemented using clean architecture principles.
The main modules are:
1) app - application UI is based on MVVM pattern, it depends on the domain module.
2) domain - it contains use case modules to fetch/save data either from local or remote storage, depends on the data module;
3) data - repository, contains model & methods to write/read data.


## Tech Stack
- Koin - used to provide dependency injection
- Retrofit 2 - OkHttp3 - request/response API
- Picasso - for image loading.
- Rooms api - for internal storage data base
- LiveData -  to see UI update with data changes.
- Data Binding - bind UI components in layouts to data sources