# run

# terminal A
sbt "runMain actors.TransformationBackend 2551"


# terminal b
sbt "runMain actors.TransformationFrontend 2552"

# terminal c

play "run -Djava.library.path=./sigar"
