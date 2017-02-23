if [ -z "$1" ]; then 
 	echo "No argument provided so running testServer with default file"
	echo "This is a file containing 20 x the Works of Shakespeare"
	echo "When server has loaded navigate to localhost:9000/lines/x and enter an integer x to fetch a line"
	cd testServer/fileLineReader/
	../activator-dist-1.3.12/bin/activator run	
else
	echo "Running testServer with input file $1"
	echo "Backing up file as backupFile.txt"
	mv file.txt backupFile.txt
        mv $1 file.txt
        echo "When server has loaded navigate to localhost:9000/lines/x and enter an integer x to fetch a line"
	cd testServer/fileLineReader/
	../activator-dist-1.3.12/bin/activator run
fi
