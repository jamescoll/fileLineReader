A testserver which takes a single url GET /lines/<number> and returns the line indicating that number.

The code is written with a controller mapping the request to a service which loads the file into a simple in-memory Java structure and
serves lines as requested.

There are some basic unit tests provided in the test package. With activator installed simply run 'activator run' to run the server on port 9000.
Run 'activator test' to run unit tests.

Shell scripts to automate this process will follow. Until they are available activator will be required to run this.

Instructions on how to install play/activator for your platform can be found at https://www.playframework.com/

