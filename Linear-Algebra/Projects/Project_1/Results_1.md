# Part 1

## Normal Equations

Result: The error using the Normal Equations method is:  17.453728532266638

## QR Decomposition

Result: The error using the QR method is:  7.272761563002539e-06

## Regularized QR Decomposition

Result: The error using regularized QR method is:  7.770549958516506e-09 (where a=1e-7 and is proved to be optimal)

### Discuss the effect of a smaller or a larger value of a

First we create a plot that shows how the error shifts in terms of a.

![Error in terms of 'a'](Screenshots\a_plot_1.PNG)

We create an extra plot that zooms in using log, to prove that between 10\^-6 and 10\^-7 the latter is optimal

![Zoom in](Screenshots\a_plot_2.PNG)

## Truncated Least Squares

Result: The error with the optimal truncation is:  4.78733839207236e-09

### Discuss the effect of more or less terms in the SVD

First we create a plot that shows how the error shifts in terms of different truncations.

![Error in terms of different truncations](Screenshots\truncation_plot_1.PNG)

Then we create an extra plot using semilog, to show that keeping 12 columns is the optimal truncation

![Semilog plot depicting the optimal truncation](Screenshots\truncation_plot_2.PNG)

## Draw conclusions on the results and try to explain the differences between the methods

The best method seems to be truncated SVD, followed by regularized QR, normal QR and finally normal equations. So the "ranking" is:

* Optimally Truncated SVD
* Regularized QR
* QR
* Normal Equations

This sequence makes sense, since normal equations are not really accurate when matrix A is in a poor condition (in our case it is). Regularized QR is also naturally better than normal QR, which is the point of the regularization as it enhances the numerical stability. Finally, SVD is much stronger than the rest of the methods, so it is expected that it has the best results. It should be noted though that regularized QR is very close.

# Part 2

## Plot the points and the fitting curve using Least Squares with Constraints

![Constrained Least Squares](Screenshots\constrained_fit.PNG)

### Compare with the Least Squares approximation without Constraints

![Least Squares without Constraints](Screenshots\not_constrained_fit.PNG)

### Which of the fits seems more reasonable?

The result of this plot is as we expected, since it is trying to minimize the errors, but it takes into account all the distances optimally. 
On the other hand, the constrained plot considered only the differences from all the points without the constraints (since the differences from the constrained points are essentially 0). These differences are not optimal anymore, becaused we "sacrificed" them in order to force the curve to pass exactly from the given 3 points. In general, it depends on the goal of the project, there is no rule of thumb that could say for example that the least squares approximation without constraints is always better. It is a trade-off that we weigh in each project and tune it to fit our exact situation. For example, we could have been informed about some predictions with a very high certainty, and in that case the constrained model could be better. All in all, both methods are useful but in different circumstances.

# Code Efficiency

* In steps 2, 3, and 4 during constrained least squares, we used np.linalg.solve like in the whole notebook. This method is the optimal method to solve linear systems like Ax = b. It uses LU decomposition, but it also contains a series of 'if' statements that save computing power. This case is one where we need these if statements, as step 2 needs forward substitution and step 3, step 4 need back substitution. The internal code of the function detects the linear system's properties ensuring efficiency. In general, this function is implemented in highly optimized C and is commonly used in large-scale problems.

* Also, @ is a bit better than np.dot() as it is more readable and consistent between languages, so it is broadly used whenever needed. In general though, np.dot() and @ are very similar.

* Finally, in the beginning we used list comprehension instead of dot product which is more stable. If we used dot product, normal equations would produce 26 as an error and not 17.