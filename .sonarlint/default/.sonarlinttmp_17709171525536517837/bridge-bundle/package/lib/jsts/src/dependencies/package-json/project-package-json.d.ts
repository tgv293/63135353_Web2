import { PackageJson as PJ } from 'type-fest';
import { Minimatch } from 'minimatch';
export interface PackageJson {
    filename: string;
    contents: PJ;
}
export declare class PackageJsons {
    readonly db: Map<string, PackageJson>;
    /**
     * Look for package.json files in a given path and its child paths.
     * node_modules is ignored
     *
     * @param dir parent folder where the search starts
     * @param exclusions glob patterns to ignore while walking the tree
     */
    searchPackageJsonFiles(dir: string, exclusions: string[]): Promise<void>;
    walkDirectory(dir: string, ignoredPatterns: Minimatch[]): void;
    /**
     * Given a filename, return all package.json files in the ancestor paths
     * ordered from nearest to furthest
     *
     * @param file source file for which we need a package.json
     */
    getPackageJsonsForFile(file: string): PackageJson[];
}
