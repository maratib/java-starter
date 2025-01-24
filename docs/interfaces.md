### Introduction to Static, Default, and Functional Interfaces in Java 8+

Java 8 introduced several enhancements to interfaces, making them more powerful and versatile. These enhancements include **static methods**, **default methods**, and the concept of **functional interfaces**. Let's explore each of these features in depth, along with their rules and use cases.

---

### **1. Static Methods in Interfaces**

Static methods in interfaces allow us to define utility or helper methods that can be directly invoked on the interface itself without requiring an instance of the implementing class.

#### **Key Characteristics**

1. Declared using the `static` keyword.
2. Can only be called using the interface name (e.g., `InterfaceName.methodName()`).
3. Cannot be overridden by implementing classes.
4. Commonly used for utility methods related to the interface.

#### **Example**

```java
interface MathUtils {
    static int add(int a, int b) {
        return a + b;
    }

    static int multiply(int a, int b) {
        return a * b;
    }
}

public class StaticMethodDemo {
    public static void main(String[] args) {
        System.out.println(MathUtils.add(5, 10));       // Output: 15
        System.out.println(MathUtils.multiply(4, 6));  // Output: 24
    }
}
```

#### **Rules**

1. Static methods belong to the interface and are not inherited by implementing classes.
2. Cannot access instance-level variables or methods.
3. Can access other static methods and static variables of the interface.

---

### **2. Default Methods in Interfaces**

Default methods allow us to provide a default implementation for methods in an interface. This feature ensures backward compatibility, enabling new functionality to be added to interfaces without breaking existing implementations.

#### **Key Characteristics**

1. Declared using the `default` keyword.
2. Can be overridden by implementing classes.
3. Useful for extending interfaces without affecting old implementations.

#### **Example**

```java
interface Vehicle {
    void start();  // Abstract method

    default void stop() {
        System.out.println("Vehicle is stopping.");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car is starting.");
    }

    // Optionally override the default method
    @Override
    public void stop() {
        System.out.println("Car has stopped.");
    }
}

public class DefaultMethodDemo {
    public static void main(String[] args) {
        Vehicle car = new Car();
        car.start();  // Output: Car is starting.
        car.stop();   // Output: Car has stopped.
    }
}
```

#### **Rules**

1. Default methods can be called on an instance of the implementing class.
2. They can be overridden in the implementing class.
3. If two interfaces provide default methods with the same signature, the implementing class must explicitly override the method to resolve ambiguity.
4. Default methods can call other default methods or static methods in the interface but cannot call instance methods of the implementing class.

#### **Diamond Problem**

If a class implements multiple interfaces with conflicting default methods, the implementing class must provide its own implementation.

```java
interface A {
    default void show() {
        System.out.println("A");
    }
}

interface B {
    default void show() {
        System.out.println("B");
    }
}

class C implements A, B {
    @Override
    public void show() {
        A.super.show();  // Explicitly call A's implementation
        B.super.show();  // Explicitly call B's implementation
        System.out.println("C");
    }
}
```

---

### **3. Functional Interfaces**

A functional interface is an interface with a single abstract method (SAM). These interfaces are the foundation of lambda expressions in Java.

#### **Key Characteristics**

1. Marked with the `@FunctionalInterface` annotation (optional but recommended for clarity and to prevent accidental addition of abstract methods).
2. Can contain any number of default or static methods.
3. Designed for use with lambda expressions and method references.

#### **Common Functional Interfaces**

- **`Runnable`**: `void run()`
- **`Callable`**: `V call()`
- **`Consumer<T>`**: `void accept(T t)`
- **`Supplier<T>`**: `T get()`
- **`Function<T, R>`**: `R apply(T t)`
- **`Predicate<T>`**: `boolean test(T t)`

#### **Example**

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);  // Single abstract method
}

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        // Using lambda expression
        Calculator addition = (a, b) -> a + b;
        Calculator multiplication = (a, b) -> a * b;

        System.out.println(addition.calculate(5, 10));       // Output: 15
        System.out.println(multiplication.calculate(4, 6));  // Output: 24
    }
}
```

#### **Rules**

1. Must have exactly one abstract method.
2. Can have multiple default and static methods.
3. The `Object` class methods (e.g., `toString()`, `equals()`, `hashCode()`) are not counted as abstract methods.
4. Lambda expressions or method references can only be used to implement functional interfaces.

---

### **Summary**

- **Static Methods**:

  - Utility methods specific to the interface.
  - Not inherited by implementing classes.

- **Default Methods**:

  - Provide default behavior.
  - Can be overridden.
  - Avoid breaking backward compatibility when new methods are added.

- **Functional Interfaces**:
  - Single abstract method (SAM).
  - Enable functional programming via lambdas and method references.
  - Recommended to use `@FunctionalInterface` annotation.

These features combined make interfaces in Java 8+ a versatile tool for writing clean, maintainable, and backward-compatible code.
