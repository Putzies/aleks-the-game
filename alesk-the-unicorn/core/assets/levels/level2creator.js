let blocks = [];

let name = process.argv[2];

if(!name) {
    console.log('You must supply a name for the level as an argument to the program');
    return;
}

const enemies = [];

const lunchboxes = [];

const energyDrinks = [];

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
    for (let i = 103; i < 300; i++) {
        blocks.push({
            X: i * 50, Y: 0
        });
    }

    // Create some platforms
    for (let i = 7; i < 15; i++) {
        blocks.push({
            X: i * 50, Y: 50 * 4
        });
    }

    
    // Create some platforms
    for (let i = 2; i < 5; i++) {
        blocks.push({
            X: i * 50, Y: 50 * 12
        });
    }

    // Create some blocks with hanging spiders
    for (let i = -10; i < 10; i++) {
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
    for (let i = 300; i < 1000; i++) {
        enemies.push({
            x: i * 10,
            y: 100,
            leftBound: -10000,
            rightBound: 10000,
        });
    }
}

function createPickups() {

    for (let i = 2; i < 5; i++) {
        lunchboxes.push({
            x: i * 50, y: 50 * 15
        });
    }

    energyDrinks.push({
        x: 2500,
        y: 100,
    });

    energyDrinks.push({
        x: 2400,
        y: 100,
    });

    energyDrinks.push({
        x: 2300,
        y: 100,
    });

    energyDrinks.push({
        x: 2600,
        y: 100,
    });

    for (let i = -1500; i < -100; i += 40) {
        energyDrinks.push({
            x: i,
            y: 120
        });
    }
}

createBlocks();
createPickups();
createALotOfDisgustingSpiders();


const level = {
    blocks,
    enemies,
    player,
    lunchboxes,
    wings: [{x: 100, y: 50 * 2}],
    energyDrinks,
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