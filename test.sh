#! bin/bash
javac -cp . epl341/SlidingPuzzle/*.java

echo -n "Enter goal state: "
read  PARAMETER
PARAMETER="$(echo $PARAMETER | awk '{print toupper($0)}')"
java -cp . epl341/SlidingPuzzle/aStar $PARAMETER
