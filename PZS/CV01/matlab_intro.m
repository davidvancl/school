% Command Window – terminal for command input
% M-files – text files, sscripts, command sequences
% Working directory – directory to store user scripts
% Workspace – set of all initialized variables

clear all;  %Deteles all variables from workspace, breakpoints and even persistent variables
close all;  %Closes all figures
clc;        %Clears command window

A = zeros(3,2); %Rectangular matrix of zeros (three rows, two columns)
B = ones(3,2);  %Rectangular matrix of ones (three rows, two columns)
rng(1);         %Seed for generating random numbers in Matlab, fixes a pseudo-random number sequence
C = rand(3,2);  %Rectangular matrix of random numbers - uniform distribution (three rows, two columns)
D = randn(3,2); %Rectangular matrix of random numbers - Gauss/normal distribution (three rows, two columns)
D_size = size(D);   %Vector of dimensions of matrix/array D (number of rows, number of columns)

a = [1,3,5,7];  %Row vector
b = [1,3,5,7];  %Column vector
E = [1,3,5;7,9,11];  %Rectangular matrix
e = E(2,3);     %Indexing in arrays, first index is 1, therefore element 11 is selected (second row, third column)
e2 = E(:,1);    %Selection of first column
e3 = E(1,:);    %Selection of first row
ea = E(:);      %Elements of matrix into column vector, orders by columns
ea_len = length(ea);    %Length of an array/vector ea 

aa = [a,a];     %Vector concatenation
aat = [a;a];    %Vector concatenation

%Condition
f = 5;
if(f<10)
    disp('f is small'); %Displays an array of characters (marked by '')
else
    disp('f is not small');
end

g = 1:2:10; %Creates a vector with linearly spaced elements, first element: step: last element (last element is smaller than the given number)

%For loop
h = zeros(10,1);
for lp=1:2:10
   h(lp)=lp;
end 

ii = 1i;     %Letters i, j, 1i mark imaginary unit in Matlab

%While loop
k = zeros(10,1);
lp=1;
while(lp<10)
   k(lp)=lp;
   lp=lp+2;
end

a_sum = a + a; %Summing of vectors/arrays
a_sub = a - a; %Subtracting of vectors/arrays
a_prod = a .* a; %Multiplication element-wise of vectors/arrays
a_div = a ./ a; %Division element-wise of vectors/arrays
at = a';       %Vector transposition
a_prod_dot = a*a';  %Dot product, matrix (inner) product

L = randn(3,3);
L_inv = inv(L); %Inverse matrix to rectangular matrix L

%Generation of harmonic signal x = sin(pi/4*n);
n = 0:100;          %Independent axis n
x = sin(pi/4*n);    %Computation of values of harmonic signal

%Signal visualization
figure;             %Opens empty window for a figure
stem(n,x);          %Lolipop graph for digital signal (stem(independent variable, dependent variable))
figure;
plot(n,x,'b');      %Line graph drawn by a blue line for given digital signal
hold on;            %Fixes figure, another line can be added to this figure
y = cos(pi/4*n);
plot(n,y,'r')       %Line graph drawn by a red line for harmonic signal y

%Complex numbers
m = 1+1i;
m_abs = abs(m);     %Absolute value of m
m_arg = angle(m);   %Argument of m
m_real = real(m);   %Real part of m
m_imag = imag(m);   %Imaginary part of m

%Function of multiple variables
%Complex exponential ke(n)=e^(j*pi/20*n) and its visualization
ke = exp(1i*pi/20*n);
ke_real = real(ke);
ke_imag = imag(ke);
figure;
plot3(n,ke_real,ke_imag);
xlabel('n[-]'); %Label for independent axis x
ylabel('real(ke)'); %Label for dependent axis y
zlabel('imag(ke)'); %Label for dependent axis z
title('Complex exponential e^{j\omega n}');   %Image title, supports basic LaTeX syntax

%Set of linear equations
%x+y=5;-1/2x+y=2
set_A = [1,1;-1/2,1];           %Matrix corresponding to this set
set_b = [5;2];                  %Column vector of the ride-hand side of the euqation set
set_sol = inv(set_A)*set_b;     %Solution through inverse matrix (or set_A\set_b)

%Visualization
%Solution of a set of linear equations is an intersection of lines
set_x = 0:0.1:5;
eq1 = -set_x + 5;
eq2 = 0.5*set_x + 2;
figure;
plot(set_x,eq1);
hold on;
plot(set_x,eq2);
legend('eq1','eq2');

%Polynomial equation
%x^2-3x+2=0
quad_sol = roots([1 -3 2]);     %Solution of a quadratic equation

%Visualization
%Solution of a quadratic equation (it it has real roots) is an intersecton of a parabola with axis x
quad_x = 0:0.1:3;
quad_y = quad_x.^2 - 3*quad_x + 2;
figure;
plot(quad_x,quad_y);
hold on;
quad_x_axis = zeros(size(quad_x));  %Values for x axis
plot(quad_x,quad_x_axis,'k');

disp('Done!!');




