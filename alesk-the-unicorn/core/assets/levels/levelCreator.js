let platforms = [];
let grassPlatforms = [];
let lavaPlatforms = [];
let spikePlatforms = [];

let name = process.argv[2];

if(!name) {
    console.log('You must supply a name for the level as an argument to the program');
    return;
}

const enemies = [
    {
        x: 400,
        y: 300,
        leftBound: 0,
        rightBound: 500,
    },
    {
        x: 700,
        y: 100,
        leftBound: 500,
        rightBound: 8000,
    }
];

const lunchboxes = [];

const player = {
    x: -200,
    y: 100,
};

function createBlocks() {

    // Create ground
    grassPlatforms.push({
        X: -5000,
        Y: 0,
        dir: 0,
        length: 500,
        hangingEnemies: [],
    });

    // Create another platform
    grassPlatforms.push({
        X: -2000,
        Y: 200,
        dir: 0,
        length: 5,
        hangingEnemies: [],
    });

    // Create lava
    lavaPlatforms.push({
        X: 1740,
        Y: 400,
        dir: 0,
        length: 5,
        hangingEnemies: [],
    });

   //Create spikes
   spikePlatforms.push({
        X: -1770,
        Y: 400,
        dir: 0,
        length: 5,
        hangingEnemies: [],
    });


    let hangingEnemies = [];
    for (let i = -30; i < 30; i++) {
        hangingEnemies.push({
            startY: 50 * 9 + 12,
            range: 200,
            x: i * 50,
            y: 50 * 9 + 12,
        });
    }

    // Create some blocks with hanging spiders
    grassPlatforms.push({
        X: -1500,
        Y: 500,
        dir: 0,
        length: 60,
        hangingEnemies,
    });
}

function createALotOfDisgustingSpiders() {
    for (let i = -300; i < -100; i++) {
        enemies.push({
            x: i * 10,
            y: 100,
            leftBound: -10000,
            rightBound: 10000,
        });
    }
}

function createPickups() {
    const lunchBox = {
        x: 0,
        y: 50 * 2
    };

    lunchboxes.push(lunchBox);
}

createBlocks();
createPickups();
//createALotOfDisgustingSpiders();


const level = {
    platforms,
    grassPlatforms,
    lavaPlatforms,
    spikePlatforms,
    enemies,
    player,
    lunchboxes,
    wings: [{x: 100, y: 50 * 2}],
    energyDrinks: [{x: 500, y: 50 * 2}],
    baguettes: [{x: -200, y: 50 * 15}],
};

const levelMetadata = {
    name,
    highScores: []
}

const fileName = name.replace(/ /g, '_').toLowerCase();

var fs = require('fs');
fs.writeFile(fileName + '.json', JSON.stringify(level), 'utf8', () => {console.log('Done writing level.')});
fs.writeFile(fileName + '.meta.json', JSON.stringify(levelMetadata, 'utf8', () => {console.log('Done writing file metadata')}));