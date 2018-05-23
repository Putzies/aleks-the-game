let name = process.argv[2];

const BLOCKWIDTH = 54;
const BLOCKHEIGHT = 51;
if(!name) {
    console.log('You must supply a name for the level as an argument to the program');
    return;
}


const Player = {
    x: 2*BLOCKWIDTH,
    y: 1*BLOCKHEIGHT + 1,
};

let enemies = [];

const levelMetadata = {
    name,
    highScores: []
}

for (let i = 0; i < 6000; i += BLOCKWIDTH) {
    enemies.push({
        x: i + 1300,
        y: 300,
        leftBound: 1300,
        rightBound: 6000,
    });
}

enemies.push({
    x: 40000,
    y: 300,
});

const level = {
    grassPlatforms: [{
        x: -5000,
        y: 0,
        dir: 0,
        length: 1000
    }],
    lavaPlatforms: [],
    spikePlatforms: [],
    enemies,
    Player,
    lunchboxes: [{
        x: 40000,
        y: 100
    }],
    wings: [{
        x: 500,
        y: 100
    }, {
        x: 3500,
        y: 100,
    }, {
        x: 1500,
        y: 1000,
    }, {
        x: 5000,
        y: 3000
    }, {
        x: 5000,
        y: 100,
    }, {
        x: 8000,
        y: 100,
    }],
    energyDrinks: [{
        x: 700,
        y: 100,
    }, {
        x: 6000,
        y: 100,
    }, {
        x: 10000,
        y: 100,
    }, {
        x: 15000,
        y: 100,
    }, {
        x: 20000,
        y: 100,
    }, {
        x: 30000,
        y: 100
    }, {
        x: 40000,
        y: 100
    }],
    baguettes: [{
        x: 1000,
        y: 100
        player: Player
    }, {
        x: 3000,
        y: 100,
        player:[player]
    }, {
        x: 5000,
        y: 100,
        player:[player]
    }, {
        x: 7000,
        y: 100,
        player:[player]
    }, {
        x: 9000,
        y: 100,
        player:[player]
    }],
};


const fileName = name.replace(/ /g, '_').toLowerCase();

var fs = require('fs');
fs.writeFile(fileName + '.json', JSON.stringify(level), 'utf8', () => {console.log('Done writing level.')});
fs.writeFile(fileName + '.meta.json', JSON.stringify(levelMetadata, 'utf8', () => {console.log('Done writing file metadata')}));
