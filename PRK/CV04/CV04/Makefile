CC=gcc
CCFLAGS=-o $@
BUILD=./bin
SRC=./src

cc:
	$(CC) -Wall hello.c -o hello
	mkdir -p bin
	mv hello ./bin

asm:
	$(CC) -S hello.c -o hello_assembly
	mkdir -p bin
	mv hello_assembly ./bin

%:
	$(CC) -Wall $(SRC)/$@.c $(CCFLAGS)
	mkdir -p $(BUILD)
	mv $@ $(BUILD)

clean:
	rm -rf bin/
