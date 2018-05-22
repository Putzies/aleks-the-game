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
    x: 0,
    y: 11*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  //P23
  grassPlatforms.push({
    x: 1*BLOCKWIDTH,
    y: 19*BLOCKHEIGHT,
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
    x: 6*BLOCKWIDTH,
    y: 8*BLOCKHEIGHT,
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
    x: 6*BLOCKWIDTH,
    y: 15*BLOCKHEIGHT,
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
    x: 6*BLOCKWIDTH,
    y: 23*BLOCKHEIGHT,
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
    x: 12*BLOCKWIDTH,
    y: 5*BLOCKHEIGHT,
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
    x: 13*BLOCKWIDTH,
    y: 25*BLOCKHEIGHT,
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
    x: 17*BLOCKWIDTH,
    y: 17*BLOCKHEIGHT,
    dir: 1,
    length: 9,
    hangingEnemies: [],
  });

  //P27
  grassPlatforms.push({
    x: 18*BLOCKWIDTH,
    y: 17*BLOCKHEIGHT,
    dir: 0,
    length: 8,
    hangingEnemies: [],
  });

  //P18
  grassPlatforms.push({
    x: 19*BLOCKWIDTH,
    y: 2*BLOCKHEIGHT,
    dir: 0,
    length: 9,
    hangingEnemies: [],
  });

  //P28
  grassPlatforms.push({
    x: 26*BLOCKWIDTH,
    y: 13*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  //P29
  lavaPlatforms.push({
    x: 30*BLOCKWIDTH,
    y: 13*BLOCKHEIGHT,
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
    x: 30*BLOCKWIDTH,
    y: 2*BLOCKHEIGHT,
    dir: 0,
    length: 6,
    hangingEnemies: [],
  });

  //P16
  grassPlatforms.push({
    x: 38*BLOCKWIDTH,
    y: 2*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  //P15
  grassPlatforms.push({
    x: 45*BLOCKWIDTH,
    y: 2*BLOCKHEIGHT,
    dir: 0,
    length: 6,
    hangingEnemies: [],
  });

  //P14
  grassPlatforms.push({
    x: 52*BLOCKWIDTH,
    y: 0*BLOCKHEIGHT,
    dir: 0,
    length: 9,
    hangingEnemies: [],
  });

  //PX
  spikePlatforms.push({
    x:63*BLOCKWIDTH,
    y:2*BLOCKHEIGHT,
    dir:0,
    length:3,
    hangingEnemies:[]
  });

  //P13
  grassPlatforms.push({
    x: 68*BLOCKWIDTH,
    y: 1*BLOCKHEIGHT,
    dir: 0,
    length: 7,
    hangingEnemies: [],
  });

  //P12
  lavaPlatforms.push({
    x: 75*BLOCKWIDTH,
    y: 1*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  //P11
  grassPlatforms.push({
    x: 79*BLOCKWIDTH,
    y: 1*BLOCKHEIGHT,
    dir: 0,
    length: 30,
    hangingEnemies: [],
  });

  //P30
  grassPlatforms.push({
    x: 35*BLOCKWIDTH,
    y: 13*BLOCKHEIGHT,
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
    x: 38*BLOCKWIDTH,
    y: 13*BLOCKHEIGHT,
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
    x: 40*BLOCKWIDTH,
    y: 13*BLOCKHEIGHT,
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
    x: 43*BLOCKWIDTH,
    y: 13*BLOCKHEIGHT,
    dir: 0,
    length: 4,
    hangingEnemies: [],
  });

  //P34
  grassPlatforms.push({
    x: 47*BLOCKWIDTH,
    y: 13*BLOCKHEIGHT,
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
    x: 54*BLOCKWIDTH,
    y: 15*BLOCKHEIGHT,
    dir: 0,
    length: 38,
    hangingEnemies: [],
  });

  //P36
  grassPlatforms.push({
    x: 92*BLOCKWIDTH,
    y: 16*BLOCKHEIGHT,
    dir: 1,
    length: 35,
    hangingEnemies: [],
  });

  //37
  grassPlatforms.push({
    x: 66*BLOCKWIDTH,
    y: 21*BLOCKHEIGHT,
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
    x: 80*BLOCKWIDTH,
    y: 21*BLOCKHEIGHT,
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
    x: 71*BLOCKWIDTH,
    y: 25*BLOCKHEIGHT,
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
