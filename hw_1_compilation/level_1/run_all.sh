echo 'starting'

echo 'Package: '
echo ' '
cd ./package
. ./run.sh

cd ..

echo 'separate_packages: '
echo ' '
cd ./separate_packages
. ./run.sh

cd ..

echo 'simple: '
echo ' '
cd ./simple
. ./run.sh

cd ..

echo 'union: '
echo ' '
cd ./union
. ./run.sh

cd ..

echo ' '
echo 'Done'