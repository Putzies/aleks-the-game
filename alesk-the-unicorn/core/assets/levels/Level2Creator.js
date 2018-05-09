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

// This level goes from Right to left so platforms are built that way

function createPlatforms(){
  // P21
  grassPlatforms.push({
    X: 0,
    Y: 11*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  //P23
  grassPlatforms.push({
    X: 1*BLOCKWIDTH,
    Y: 19*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  lunchboxes.push({
    x: 2*BLOCKWIDTH,
    y: 20* BLOCKHEIGHT
  });

  //P20
  grassPlatforms.push({
    X: 6*BLOCKWIDTH,
    Y: 8*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  lunchboxes.push({
    x: 15*BLOCKWIDTH,
    y: 9* BLOCKHEIGHT
  });

  //P22
  grassPlatforms.push({
    X: 6*BLOCKWIDTH,
    Y: 15*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  lunchboxes.push({
    x: 7*BLOCKWIDTH,
    y: 16* BLOCKHEIGHT
  });

  //P24
  grassPlatforms.push({
    X: 6*BLOCKWIDTH,
    Y: 23*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  lunchboxes.push({
    x: 6*BLOCKWIDTH,
    y: 24* BLOCKHEIGHT
  });

  //P19
  grassPlatforms.push({
    X: 12*BLOCKWIDTH,
    Y: 5*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  lunchboxes.push({
    x: 16*BLOCKWIDTH,
    y: 6* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 17*BLOCKWIDTH,
    y: 5* BLOCKHEIGHT
  });

  //P25
  grassPlatforms.push({
    X: 13*BLOCKWIDTH,
    Y: 25*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  lunchboxes.push({
    x: 13*BLOCKWIDTH,
    y: 25* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 18*BLOCKWIDTH,
    y: 27* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 20*BLOCKWIDTH,
    y: 28* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 21*BLOCKWIDTH,
    y: 26* BLOCKHEIGHT
  });

  lunchboxes.push({
    x: 21*BLOCKWIDTH,
    y: 24* BLOCKHEIGHT
  });
  //P26
  grassPlatforms.push({
    X: 17*BLOCKWIDTH,
    Y: 17*BLOCKHEIGHT,
    dir: 1,
    length: 9,
    hangingEnemies: [],
  });

  //P27
  grassPlatforms.push({
    X: 18*BLOCKWIDTH,
    Y: 17*BLOCKHEIGHT,
    dir: 0,
    length: 8,
    hangingEnemies: [],
  });

  //P18
  grassPlatforms.push({
    X: 19*BLOCKWIDTH,
    Y: 2*BLOCKHEIGHT,
    dir: 0,
    length: 9,
    hangingEnemies: [],
  });

  //P28
  grassPlatforms.push({
    X: 26*BLOCKWIDTH,
    Y: 13*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  //P29
  lavaPlatforms.push({
    X: 30*BLOCKWIDTH,
    Y: 13*BLOCKHEIGHT,
    dir: 0,
    length: 5,
    hangingEnemies:  [{
          startY: 13*BLOCKHEIGHT,
          range: 50,
          x: 32*BLOCKWIDTH,
          y: 13*BLOCKHEIGHT,
      }]
  })

  //P17
  grassPlatforms.push({
    X: 30*BLOCKWIDTH,
    Y: 2*BLOCKHEIGHT,
    dir: 0,
    length: 6,
    hangingEnemies: [],
  });

  //P16
  grassPlatforms.push({
    X: 38*BLOCKWIDTH,
    Y: 2*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  //P15
  grassPlatforms.push({
    X: 45*BLOCKWIDTH,
    Y: 2*BLOCKHEIGHT,
    dir: 0,
    length: 6,
    hangingEnemies: [],
  });

  //P14
  grassPlatforms.push({
    X: 52*BLOCKWIDTH,
    Y: 0*BLOCKHEIGHT,
    dir: 0,
    length: 9,
    hangingEnemies: [],
  });

  //PX
  spikePlatforms.push({
    X:63*BLOCKWIDTH,
    Y:2*BLOCKHEIGHT,
    dir:0,
    length:3,
    hangingEnemies:[]
  });

  //P13
  grassPlatforms.push({
    X: 68*BLOCKWIDTH,
    Y: 1*BLOCKHEIGHT,
    dir: 0,
    length: 7,
    hangingEnemies: [],
  });

  //P12
  lavaPlatforms.push({
    X: 75*BLOCKWIDTH,
    Y: 1*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  //P11
  grassPlatforms.push({
    X: 79*BLOCKWIDTH,
    Y: 1*BLOCKHEIGHT,
    dir: 0,
    length: 30,
    hangingEnemies: [],
  });

  //P30
  grassPlatforms.push({
    X: 35*BLOCKWIDTH,
    Y: 13*BLOCKHEIGHT,
    dir: 0,
    length: 3,
    hangingEnemies:  [{
            startY: 13*BLOCKHEIGHT,
            range: 50,
            x: 36*BLOCKWIDTH,
            y: 13*BLOCKHEIGHT,
        }],
  });

  //P31
  lavaPlatforms.push({
    X: 38*BLOCKWIDTH,
    Y: 13*BLOCKHEIGHT,
    dir: 0,
    length: 2,
    hangingEnemies:  [{
            startY: 13*BLOCKHEIGHT,
            range: 50,
            x: 38.5*BLOCKWIDTH,
            y: 13*BLOCKHEIGHT,
        }],
  });

  //P32
  grassPlatforms.push({
    X: 40*BLOCKWIDTH,
    Y: 13*BLOCKHEIGHT,
    dir: 0,
    length: 3,
    hangingEnemies:  [{
            startY: 13*BLOCKHEIGHT,
            range: 50,
            x: 41.5*BLOCKWIDTH,
            y: 13*BLOCKHEIGHT,
        }],
  });

  //P33
  lavaPlatforms.push({
    X: 43*BLOCKWIDTH,
    Y: 13*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  //P34
  grassPlatforms.push({
    X: 47*BLOCKWIDTH,
    Y: 13*BLOCKHEIGHT,
    dir: 0,
    length: 5,
    hangingEnemies:  [{
            startY: 13*BLOCKHEIGHT,
            range: 50,
            x: 50*BLOCKWIDTH,
            y: 13 *BLOCKHEIGHT,
        }],
  });

  //P35
  grassPlatforms.push({
    X: 54*BLOCKWIDTH,
    Y: 15*BLOCKHEIGHT,
    dir: 0,
    length: 38,
    hangingEnemies: [],
  });

  //P36
  grassPlatforms.push({
    X: 92*BLOCKWIDTH,
    Y: 16*BLOCKHEIGHT,
    dir: 1,
    length: 35,
    hangingEnemies: [],
  });

  //37
  grassPlatforms.push({
    X: 66*BLOCKWIDTH,
    Y: 21*BLOCKHEIGHT,
    dir: 0,
    length: 5,
    hangingEnemies:  [{
            startY: 21*BLOCKHEIGHT,
            range: 50,
            x: 68.5*BLOCKWIDTH,
            y: 21*BLOCKHEIGHT,
        }],
  });

  //P38
  grassPlatforms.push({
    X: 80*BLOCKWIDTH,
    Y: 21*BLOCKHEIGHT,
    dir: 0,
    length: 5,
    hangingEnemies:  [{
            startY: 21*BLOCKHEIGHT,
            range: 50,
            x: 82.5*BLOCKWIDTH,
            y: 21*BLOCKHEIGHT,
        }],
  });

  //P39
  grassPlatforms.push({
    X: 71*BLOCKWIDTH,
    Y: 25*BLOCKHEIGHT,
    dir: 0,
    length: 9,
    hangingEnemies: [],
  });
}

function createLunchBoxes(){

}

function createEnemies(){

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
}

  createPlatforms();
  createEnemies();
  createLunchBoxes();

  const player = {
      x: 106*BLOCKWIDTH,
      y: 2*BLOCKHEIGHT + 1,
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
