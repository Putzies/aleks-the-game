let blocks = [];

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
    for (let i = -100; i < 100; i++) {
        blocks.push({
            X: i * 50, Y: 0
        });
    }

    // Create some really large stairs
    for (let i = 0; i < 1000; i++) {
        blocks.push({
            X: 50 * 50 + i * 50, Y: 6 * 50 + 50 * Math.round((i / 6))
        });
    }

    // Create some blocks with hanging spiders
    for (let i = -30; i < 30; i++) {
        blocks.push({
            X: i * 50,
            Y: 50 * 10,
            hangingEnemy: {
                startY: 50 * 9 + 12,
                range: 200,
                x: i * 50,
                y: 50 * 9 + 12,
            }
        });
    }
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
    blocks,
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