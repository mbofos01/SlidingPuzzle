#! /bin/bash
javac -cp . epl341/SlidingPuzzle/*.java

for runs in WWWEBBB WWEWBBB WEWWBBB WWWBBBE WWWBEBB EWWWBBB
do
	echo "--------------------------------------"
	java -cp . epl341/SlidingPuzzle/aStar $runs
done
