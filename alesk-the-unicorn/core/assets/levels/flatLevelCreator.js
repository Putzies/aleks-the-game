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

let enemies = [];

const levelMetadata = {
    name,
    highScores: []
}

for (let i = 0; i < 500; i += BLOCKWIDTH) {
    enemies.push({
        x: i + 1300,
        y: 300
    });
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
    enemies,
    player,
    lunchboxes: [{
        x: 6000,
        y: 100
    }],
    wings: [{
        x: 600,
        y: 100
    }],
    energyDrinks: [{
        x: 800,
        y: 100,
    }],
    baguettes: [{
        x: 1000,
        y: 100
    }],
};


const fileName = name.replace(/ /g, '_').toLowerCase();

var fs = require('fs');
fs.writeFile(fileName + '.json', JSON.stringify(level), 'utf8', () => {console.log('Done writing level.')});
fs.writeFile(fileName + '.meta.json', JSON.stringify(levelMetadata, 'utf8', () => {console.log('Done writing file metadata')}));
