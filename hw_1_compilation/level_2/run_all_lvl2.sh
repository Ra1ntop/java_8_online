echo 'starting level 2'

echo 'depends: '
echo ' '
cd ./depends
. ./run.sh

cd ..

echo 'final_1: '
echo ' '
cd ./final_1
. ./run.sh

cd ..

echo 'final_2: '
echo ' '
cd ./final_2
. ./run.sh

cd ..

echo 'minimal_jar: '
echo ' '
cd ./minimal_jar
. ./run.sh

cd ..

echo 'minimal_proj: '
echo ' '
cd ./minimal_proj
. ./run.sh

cd ..

echo ' '
echo 'Done'