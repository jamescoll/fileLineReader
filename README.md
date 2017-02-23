A testserver which takes a single url GET /lines/<number> and returns the line indicating that number.

The code is written with a controller mapping the request to a service which loads the file into a simple in-memory Java structure and
serves lines as requested.

There are some basic unit tests provided in the test package. With activator installed simply run 'activator run' to run the server on port 9000.
Run 'activator test' to run unit tests.

Shell scripts to automate this process will follow. Until they are available activator will be required to run this.

Instructions on how to install play/activator for your platform can be found at https://www.playframework.com/

Note that the fileLineServer is a singleton and reads the file into memory (50x Works of Shakespeare) when the first request is made.
This has the effect that the first request may lag but all subsequent requests take advantage of the scaling and non-blocking features of play.


<b>How does your system work? </b>

This system uses a micro-service pattern running a controller to handle the REST
requests, a service to load the file into a data structure, a module to bootstrap the
service and two shell scripts to build and run the system. It relies for performance
on the Play framework which handles much of the boilerplate normally necessary for
such a system. 

Information about Play and Play performance can be retrieved here:

https://www.playframework.com/

All that is needed to run this server is downloading the build.sh file which
will do all of the following work: 
<ul>
<li>creating a directory for the test server</li>
<li>downloading the test server into the directory</li>
<li>downloading any dependencies (sbt...a maven wrapper)</li>
<li>running some very trivial unit tests</li>
<li>prepping the system for running via run.sh</li>
</ul>
<i>Please ensure that build.sh and run.sh are in the same directory</i>


<b>What do we need to build your system?</b>

A linux or mac system. The sh files will do the rest.


<b> How will your system perform with a 1 GB file? a 10 GB file? a 100 GB file?</b>

There was not time to test the system with large files. The default file is < 10mb. However, 
this system is far from highly performant for very large files. It is stateful, for a start, and uses
buffered io and default datastructures. I will comment more on what production should look like below.

<b> How will your system perform with 100 users? 10000 users? 1000000 users?</b>

This system should scale better for multiple users as it is backed by a solid framework and the service
only does one load into memory. So, leaving aside the file size concerns, this system will experience 
bottlenecks that are limitations of the framework rather than of the coding patterns used.

<b>What documentation, websites, papers, etc did you consult in doing this
assignment?</b>

None as I was relying on my prior knowledge of the Play framework and Java data structures. But I can
provide a list of texts I have consulted over time if required.

<b> What third-party libraries or other tools does the system use? How did you
choose each library or framework you used? </b>

This system uses the Play framework. For a quote on its performance:

"Play is a stateless, asynchronous, and non-blocking framework that uses an underlying fork-join thread pool to do work stealing for network operations, and can leverage Akka for user level operations."

https://www.lightbend.com/blog/why-is-play-framework-so-fast

<b> How long did you spend on this exercise? If you had unlimited more time to
spend on this, how would you spend it and how would you prioritize each item? </b>

About 30 minutes coding and about 45 minutes documenting and refactoring.

If I had more time I would deal with two issues I did not tackle here as first priorities:

1.) I would deal with the issues arising from very large file sizes and avoid using a stateful service
to handle these cases. In this service I simply dump the file into a Java structure (ArrayList) rather
than taking a functional and scalable approach (i.e. by using streams and nio patterns)
2.) I would deal with the issues arising from lines which exceed String (and, in fact, even this would be poor as
getting much smaller but large Strings into a datastructure will quickly cause memory and performance isssues).
I would most likely take the approach of indexing the file without doing structure loading and then returning large
lines in chunks. There are various approaches to this.

The sky is the limit after this. It may be, for certain use cases, that db loading is the best solution
for a stateless service...there are many other possiblities but the two big problems at a code level are above.

Outside of the coding level, it may be that a distributed system is necessary and there are also several approaches to this 
which we might discuss in depth.


<b> If you were to critique your code, what would you have to say about it?</b>

This is POC code and not ready for any production environment in which large scale is necessary. But it also
does not pretend to be. Many of my criticisms are outlined above and some can be discussed in depth as desired.
