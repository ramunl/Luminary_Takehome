
The project is implemented using clean architecture principles.

The main modules are:
1) app - application UI is based on MVVM pattern, it depends on the domain module, it knows nothing about the data module.
2) domain - it contains use case modules to fetch/save data either from local or remote storage, it depends on the data module;
3) data - repository, it contains model & methods to write/read data.