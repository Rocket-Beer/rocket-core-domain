# rocket-core-domain
Core library for domain layer

## Preconditions
Conect to repository with GitHub credentials located in "local.properties" archive and config maven properties in "settings.gradle"

local.properties has to contain:
> github.username=*******  
> github.token=*********************

settings.gradle has contain Rocket-Beer connection with maven
~~~
maven {  
    Properties properties = new Properties()  
    properties.load(file('local.properties').newDataInputStream())  
    url "https://maven.pkg.github.com/Rocket-Beer/*"  
    
    credentials {  
        username = properties.getProperty("github.username")  
        password = properties.getProperty("github.token")  
    }  
}
~~~

## Core Domain user manual
Core Domain package ease to implement use cases and manage success and error responses.

### Implement Rocket core-viewmodel package
~~~
implementation "com.rocket.core:core-domain:0.0-beta0"
~~~

#### Either
Class to manage success and error responses

#### UseCase
Class to manage use cases or business logic.  
Use cases should be entry params object to manage request data.

#### Failure
Class to manage errors

##### UseCase example
~~~
class MyUseCase (
    private val repository: MyRepository,
): UseCase<Either<Failure, ObjectResponse?>, MyUseCase.Params?>() {
    override fun run(params: Params?): Either<Failure, ObjectResponse?> =
        repository.getData(params?.data).fold(
            {
                //Throw failure
            },
            {
                //Throw success response
            }

    data class Params(val data: String)
}
~~~

## Packages
core-domain --> 0.0-beta0 
