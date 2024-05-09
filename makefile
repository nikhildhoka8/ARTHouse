run: AuctionHouse.java
	javac -g AuctionHouse.java
	java AuctionHouse

Client.class: Client.java
	javac -g Client.java

runClient: Client.class
	java Client

clean:
	rm *.class

cleanData:
	rm *.dat
