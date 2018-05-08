let name = process.argv[2];

const BLOCKWIDTH = 54;
const BLOCKHEIGHT = 51;
if(!name) {
    console.log('You must supply a name for the level as an argument to the program');
    return;
}


const player = {
    x: 2*BLOCKWIDTH,
    y: 1*BLOCKHEIGHT + 1,
};

const levelMetadata = {
    name,
    highScores: []
}

const level = {
    grassPlatforms: [{
        X: -5000,
        Y: 0,
        dir: 0,
        length: 1000
    }],
    lavaPlatforms: [],
    spikePlatforms: [],
    enemies: [{
        x: 400,
        y: 300,
        leftBound: 0,
        rightBound: 500,
    }],
    player,
    lunchboxes: [{
        x: 600,
        y: 100
    }],
    wings: [],
    energyDrinks: [],
    baguettes: [],
};


const fileName = name.replace(/ /g, '_').toLowerCase();

var fs = require('fs');
fs.writeFile(fileName + '.json', JSON.stringify(level), 'utf8', () => {console.log('Done writing level.')});
fs.writeFile(fileName + '.meta.json', JSON.stringify(levelMetadata, 'utf8', () => {console.log('Done writing file metadata')}));
