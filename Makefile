JAVAC:=javac
SOURCE:=$(wildcard *.java)
CLASS:=$(SOURCE:.java=.class)

all:$(CLASS)

%.class: %.java
	${JAVAC} $<

clean:
	rm *.class
