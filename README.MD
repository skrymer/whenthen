# When Then 
Are you tired of writing code like this:
```java
    if(1==1){
        System.out.println("1 is equal to 1");
    }
    
    if(true){
        throw new IllegalArgumentException("Some arg");
    }
```
then:
```java

    when(1==1).then(() -> System.out.println("1 is equal to 1"));
    when(1==1).thenThrowIllegalArgument("Some arg"));
```
see WhenThenSpec for more examples