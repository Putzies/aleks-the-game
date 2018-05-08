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

function createPlatformTier1(){
  // P1
  grassPlatforms.push({
    X: 0,
    Y: 0,
    dir: 0,
    length: 20,
    hangingEnemies: [],
  });

  //P2
  grassPlatforms.push({
    X:22*BLOCKWIDTH,
    Y:0,
    dir: 0,
    length: 10,
    hangingEnemies: [],
  });

  //P3
  grassPlatforms.push({
    X:36*BLOCKWIDTH,
    Y:0,
    dir:0,
    length: 14,
    hangingEnemies: [],
  });

  //P4
  grassPlatforms.push({
    X:54*BLOCKWIDTH,
    Y:0,
    dir:0 ,
    length: 5,
    hangingEnemies: [],
  });

  //P5
  grassPlatforms.push({
    X:60*BLOCKWIDTH,
    Y:2*BLOCKHEIGHT,
    dir:0 ,
    length: 5,
    hangingEnemies: [],
  });

  //P6
  grassPlatforms.push({
    X:68*BLOCKWIDTH,
    Y:2*BLOCKHEIGHT,
    dir:0 ,
    length: 5,
    hangingEnemies: [],
  });

  //P7
  grassPlatforms.push({
    X:75*BLOCKWIDTH,
    Y:0*BLOCKHEIGHT,
    dir:0 ,
    length: 16,
    hangingEnemies: [],
  });

  //P8
  grassPlatforms.push({
    X:97*BLOCKWIDTH,
    Y:0*BLOCKHEIGHT,
    dir:0 ,
    length: 11,
    hangingEnemies: [],
  });

  //P9
  grassPlatforms.push({
    X:107*BLOCKWIDTH,
    Y:1*BLOCKHEIGHT,
    dir: 1,
    length: 5,
    hangingEnemies: [],
  });

  //P10
  grassPlatforms.push({
    X:108*BLOCKWIDTH,
    Y:5*BLOCKHEIGHT,
    dir:0 ,
    length: 5,
    hangingEnemies: [],
  });

}

function createLunchBoxTier1(){
  //Left to right filling of the first tier.
  lunchboxes.push({
    x: 12*BLOCKWIDTH,
    y: 1* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 23*BLOCKWIDTH,
    y: 1.25* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 24.5 * BLOCKWIDTH,
    y: 2.5 * BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 26.5*BLOCKWIDTH,
    y: 3.5* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 28.5*BLOCKWIDTH,
    y: 2.5* BLOCKHEIGHT
  });
  lunchboxes.push({
    x: 30*BLOCKWIDTH,
    y: 1* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 48*BLOCKWIDTH,
    y: 1.25* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 56*BLOCKWIDTH,
    y: 1* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 62*BLOCKWIDTH,
    y: 3* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 65.5*BLOCKWIDTH,
    y: 4* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 69*BLOCKWIDTH,
    y: 3* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 76*BLOCKWIDTH,
    y: 1* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 82*BLOCKWIDTH,
    y: 1* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 88*BLOCKWIDTH,
    y: 1* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 91*BLOCKWIDTH,
    y: 2* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 93*BLOCKWIDTH,
    y: 3* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 95*BLOCKWIDTH,
    y: 3* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 97 * BLOCKWIDTH,
    y: 2 * BLOCKHEIGHT
  });

}

function createEnemiesTier1(){

  enemies.push({
    x: 43*BLOCKWIDTH,
    y: 1*BLOCKWIDTH + 1,
    leftBound: 36*BLOCKWIDTH,
    rightBound: 50*BLOCKWIDTH
  });

  enemies.push({
    x: 80*BLOCKWIDTH,
    y: 1*BLOCKWIDTH + 1,
    leftBound: 75*BLOCKWIDTH,
    rightBound: 91*BLOCKWIDTH
  });

  enemies.push({
    x: 84*BLOCKWIDTH,
    y: 1*BLOCKWIDTH + 1,
    leftBound: 75*BLOCKWIDTH,
    rightBound: 91*BLOCKWIDTH
  });
}
function createPlatformTier2(){

}

function createPlatformTier3(){

}


createPlatformTier1();
createEnemiesTier1();
createLunchBoxTier1();

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
