createJar: *.*
	jar cvfm AuctionHouse.jar manifest.txt *.*
	
runAuctionHouse: AuctionHouse.java
	javac -g AuctionHouse.java

run: AuctionHouse.jar
	java -jar AuctionHouse.jar

Client.class: Client.java
	javac -g Client.java

runClient: Client.class
	java Client

clean:
	rm *.class

cleanData:
	rm *.dat
