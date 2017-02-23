#!/bin/sh
echo "******FILE LINE SERVER******"
echo "This process will take several minutes"
echo "Please execute the run.sh file without changing directory after you have run this script"
echo "Making new directory"
mkdir testServer
echo "Entering new directory"
cd testServer
echo "Downloading activator"
curl https://downloads.typesafe.com/typesafe-activator/1.3.12/typesafe-activator-1.3.12.zip > activator.zip
echo "Unzipping activator"
unzip activator.zip
echo "Pulling code from Git"
git clone https://github.com/jamescoll/fileLineReader.git
echo "Entering core directory"
cd fileLineReader
echo "Running unit tests - this may take some time as it will also download dependencies"
../activator-dist-1.3.12/bin/activator test
echo "Returning to directory root"
echo "If you do not provide a filename the script will run with the default file which is multiple times the Works Of Shakespeare"
