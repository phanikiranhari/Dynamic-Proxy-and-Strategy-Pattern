[
  adopted from http://www.cs.rochester.edu/u/www/courses/171/Fall-03/files/readme.txt
  by Deger Cenk Erdil
  for CS654 Distributed Systems
  This is a template README file about how you should form your own README file.
  In general,
        you should remove anything in between square brackets (i.e. [..]), and
        you should replace anything in between <> with a value.
]

CS542 Design Patterns
Spring 2016
PROJECT <4> README FILE

Due Date: <PROJECT DUE DATE, IN FORMAT: Sunday, May 8, 2016>
Submission Date: <DATE YOU SUBMIT, IN FORMAT: Sunday, May 8, 2016>
Grace Period Used This Project: 0 Days
Grace Period Remaining: 0 Days
Author(s): PHANIKIRAN HARI
e-mail(s): phari2@binghamton.edu


PURPOSE:

[
  To implement Dynamic Proxy and strategy patterns in Java
]

PERCENT COMPLETE:

[
 100
]

PARTS THAT ARE NOT COMPLETE:

[
 NONE
]

BUGS:

[
 None
]

FILES:

[
  Input file has to be given as one of the command line arguments for the program(deser mode) to compile
]

SAMPLE OUTPUT:

[

{myInt = 137774346, myLong = 7924745610425347551, myString = wXCP2VvJtw, myBool =  true}
{myDoubleT = 0.16689408185640664, myShortT = 22423, myFloatT = 0.17358619, myCharT =  n}

{myInt = 995830301, myLong = -1416583729842834883, myString = WeymByWEu4, myBool =  false}
{myDoubleT = 0.0035150975359825587, myShortT = 21962, myFloatT = 0.097717345, myCharT =  i}

{myInt = 759142908, myLong = -8012711014790082778, myString = kXAdPbh5Mo, myBool =  false}
{myDoubleT = 0.7143424458608859, myShortT = 28858, myFloatT = 0.32504106, myCharT =  s}

{myInt = -1771041612, myLong = -7022830574395215421, myString = T1QhbxtJp3, myBool =  false}
{myDoubleT = 0.6184267467993799, myShortT = 31235, myFloatT = 0.096782625, myCharT =  v}

{myInt = 81576204, myLong = 8927350928401994061, myString = BtX4EUvZZq, myBool =  true}
{myDoubleT = 0.7180104497906926, myShortT = 16933, myFloatT = 0.8118495, myCharT =  n}

]

TO COMPILE:

[
  Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

## To compile: 
ant -buildfile src/build.xml all

## To run by specifying arguments from command line [similarly for the 2nd argument and so on ...]
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=deser -Darg1=5 -Darg2=output.txt

## To create tarball for submission
ant -buildfile src/build.xml tarzip

## To untar the tarball submitted
ant -buildfile src/build.xml untarzip

]

TO RUN:

[
## To run by specifying args in build.xml (just for debugging, not for submission)
ant -buildfile src/build.xml run
]

EXTRA CREDIT:

[
  N/A
]


BIBLIOGRAPHY:

This serves as evidence that we are in no way intending Academic Dishonesty.
<PHANIKIRAN HARI>
[
  
]

ACKNOWLEDGEMENT:

[
 
]

CHOICE OF DATA STRUCTURE[

]