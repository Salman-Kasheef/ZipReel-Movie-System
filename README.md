# ZipReel Movie Management System

## Overview
ZipReel is a movie content management system designed for a streaming platform. It provides efficient movie searches and access through a multi-level caching mechanism.

### Key Features
- **Multi-Level Caching**:
  - **L1 Cache**: User-specific cache with Least Recently Used (LRU) eviction policy.
  - **L2 Cache**: Global cache with Least Frequently Used (LFU) eviction policy.
- **Primary Store**: All movie data is stored in memory.
- **Search Capabilities**:
  - Search by title, genre, year, or rating.
  - Support for combined filters like genre, year, and minimum rating.
- **Cache Management**:
  - View cache statistics.
  - Clear cache levels individually.

### Commands
#### Adding Movies
```bash
ADD_MOVIE <id> <title> <genre> <year> <rating>
Example: ADD_MOVIE 1 "Inception" "Sci-Fi" 2010 9.5
