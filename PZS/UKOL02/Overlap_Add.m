clear all;
close all;
clc;

%Overlap-Add
%Compute the convolution of a long signal and a much shorter impulse response of length L using
%overlap-add method
%Compare the output of the block-wise convolution with the Matlab function conv (computed using the definition formula)
%Compare computational time consumpiton of both approaches

%%
L=1024; %Length of the impulse response
npt = nextpow2(2*L);
M=(2^npt-L)+1;         %One of the possibilities for a block length, M+L-1 must be a power of two, due to fft
maxNumCompThreads(1);   %Let us compare the paralelization in Matlab to compare the results achieved on a single thread

N = 1e8;
x = randn(N, 1);
L = 2048;
h = randn(L, 1);
% [x,Fs]=audioread('waltz.wav');  %Load audio signal
% x=x(:,1);
% x=repmat(x,20,1);               %Replicate it 20x, to obtain a very long x

% h=fir1(L-1,0.2);                %Low-pass filter impulse response (alternatively, it can be random, we are not interested in the result, just in the computational complexity)
tmpH=zeros(M+L-1,1);
tmpH(1:L)=h;
tmpH=fft(tmpH);                 %DFT of the impulse reponse

tic;
y1=conv(x,h);                   %Coovnolution by definition
ty1=toc;

tic;
bNum = ceil(length(x)/M);       %Number of samples
y2=zeros(size(y1));
ty2=0;
for lp=1:bNum
   start=1+(lp-1)*M;
   stop=min(lp*M,length(x));    %Select a signal frame (checks for the last shorter sample)
   fLen=stop-start+1;           %True number of samples of a frame (due to the last frame)
   
   tmpX=zeros(M+L-1,1);         
   tmpX(1:fLen)=x(start:stop);  %Zero-padding, to make the circular convolution equal to the linear one
   tmpX=fft(tmpX);              %DFT of the frame
   tmpy2=ifft(tmpX.*tmpH);      %Circular/linear convolution
   tmpy2=tmpy2(1:fLen+L-1);     %Shortening of the output to match the length of the input (due to the last block)
   
   y2(start:start+length(tmpy2)-1)=y2(start:start+length(tmpy2)-1)+tmpy2;   %Summation of the current block with the previous one and its saving in the output signal
   
end
ty2=ty2+toc;

disp('Total square error between results:');
sum((y1-y2).^2)
disp('Time required by convolution by definition:');
ty1
disp('Time required by overlap-add:');
ty2
