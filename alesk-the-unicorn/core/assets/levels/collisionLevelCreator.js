let platforms = [];
let grassPlatforms = [];
let lavaPlatforms = [];
let spikePlatforms = [];
let enemies = [];
let lunchboxes = [];
let name = process.argv[2];

const BLOCKWIDTH = 54;
const BLOCKHEIGHT = 51;
if(!name) {
    console.log('You must supply a name for the level as an argument to the program');
    return;
}

function createPlatforms(){
    // P1
    grassPlatforms.push({
      X: 0,
      Y: 0,
      dir: 0,
      length: 5,
      hangingEnemies: [],
    });

    // P1
    grassPlatforms.push({
      X: 0,
      Y: 7*BLOCKHEIGHT,
      dir: 0,
      length: 5,
      hangingEnemies: [],
    });

    // P1
    grassPlatforms.push({
      X: 0,
      Y: 16*BLOCKHEIGHT,
      dir: 0,
      length: 5,
      hangingEnemies: [],
    });

    //P2
    grassPlatforms.push({
      X:7*BLOCKWIDTH,
      Y:0,
      dir: 0,
      length: 5,
      hangingEnemies: [],
    });

    //P4
    grassPlatforms.push({
      X:16*BLOCKWIDTH,
      Y:0,
      dir:0 ,
      length: 5,
      hangingEnemies: [],
    });

    //P5
    grassPlatforms.push({
      X:22*BLOCKWIDTH,
      Y:2*BLOCKHEIGHT,
      dir:0 ,
      length: 5,
      hangingEnemies: [],
    });

    //P6
    grassPlatforms.push({
      X:28*BLOCKWIDTH,
      Y:0,
      dir:0 ,
      length: 5,
      hangingEnemies: [],
    });
}

function createLunchBox(){
  //Left to right filling of the first tier.
  lunchboxes.push({
    x: 500*BLOCKWIDTH,
    y: 130* BLOCKHEIGHT
  });
}


createPlatforms();
createLunchBox();

const player = {
    x: 2*BLOCKWIDTH,
    y: 1*BLOCKHEIGHT + 1,
};

const levelMetadata = {
    name,
    highScores: []
}

const level = {
    platforms,
    grassPlatforms,
    lavaPlatforms,
    spikePlatforms,
    enemies,
    player,
    lunchboxes,
    wings: [],
    energyDrinks: [],
    baguettes: [],
};


const fileName = name.replace(/ /g, '_').toLowerCase();

var fs = require('fs');
fs.writeFile(fileName + '.json', JSON.stringify(level), 'utf8', () => {console.log('Done writing level.')});
fs.writeFile(fileName + '.meta.json', JSON.stringify(levelMetadata, 'utf8', () => {console.log('Done writing file metadata')}));
