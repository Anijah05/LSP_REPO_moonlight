# Question 2 Design Explanation

The Template Method pattern is used in the `Report` class through the `generateReport()` method. This method defines the fixed workflow: `loadData()`, `formatHeader()`, `formatBody()`, and `formatFooter()`. The subclasses `StudentReport` and `CourseReport` override the individual steps while keeping the overall workflow the same. The driver demonstrates polymorphism by storing both report types in a `List<Report>` and calling `generateReport()` on each object.
