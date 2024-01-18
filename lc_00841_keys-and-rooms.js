/**
 * @param {number[][]} rooms
 * @return {boolean}
 */
var canVisitAllRooms = function (rooms) {
    var visitedRooms = []
    var visitedRoomsFlag = {}
    var keysAvailable = [0]

    while (keysAvailable.length) {
        currentRoom = keysAvailable.shift();
        if (visitedRoomsFlag[currentRoom] === true) continue;

        visitedRooms.push(currentRoom);
        visitedRoomsFlag[currentRoom] = true;
        newKeys = rooms[currentRoom];
        keysAvailable.push(...newKeys);
    }

    if (visitedRooms.length === rooms.length) {
        return true
    }
    return false;
};
