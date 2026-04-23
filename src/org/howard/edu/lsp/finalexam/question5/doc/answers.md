# Question 5

## Heuristic 1:

Name:
Keep data private.

Explanation:
This heuristic improves maintainability by protecting the internal state of a class from outside misuse. When data is private, the class controls how that data is accessed or changed. In lecture, this idea was illustrated through examples where instance variables should not be directly exposed because outside classes could change them incorrectly.

## Heuristic 2:

Name:
Minimize public methods.

Explanation:
This heuristic improves readability and maintainability by keeping the public interface of a class small and focused. Public methods should represent behavior that outside classes actually need. In lecture, this was connected to methods like `getNextId()`, which should not be public because it is an internal implementation detail.

## Heuristic 3:

Name:
Distribute system intelligence horizontally as uniformly as possible.

Explanation:
This heuristic improves maintainability by avoiding one class that does too much work while other classes do very little. Responsibilities should be spread across classes so each class has a clear role. In lecture, this was illustrated through object-oriented design examples where behavior should be placed in the class that owns the related data or responsibility.
