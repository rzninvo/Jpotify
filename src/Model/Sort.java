package Model;

import java.util.*;

public class Sort
{
    private Vector<Music> musics;

    public Sort(Vector<Music> musics)
    {
        this.musics = musics;
    }

    public Vector<Music> alphabeticalAscending()
    {

        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getName().compareTo(musics.get(j).getName()) > 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public Vector<Music> alphabeticalDescending()
    {

        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getName().compareTo(musics.get(j).getName()) <= 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public Vector<Music> alphabeticalAlbumAscending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getAlbum().compareTo(musics.get(j).getAlbum()) > 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public Vector<Music> alphabeticalAlbumDescending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getAlbum().compareTo(musics.get(j).getAlbum()) <= 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public Vector<Music> alphabeticalArtistAscending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getArtist().compareTo(musics.get(j).getArtist()) > 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public Vector<Music> alphabeticalArtistDescending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getArtist().compareTo(musics.get(j).getArtist()) <= 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }

    public Vector<Music> shuffle()
    {
//        int count = musics.size();
//        HashMap<Integer, Integer> indexes = new HashMap<>();
//        Random random = new Random();
//        Integer index = 0;
//        while (index < count)
//        {
//            int rnd = random.nextInt(count);
//            if (indexes.get(rnd)==null)
//            {
//                indexes.put(rnd,index);
//                index++;
//            }
//        }
//        for (Integer i = 0; i < musics.size(); i++)
//        {
            Collections.shuffle(musics);
//            Music temp = musics.get(i);
//            musics.set(i, musics.get(indexes.get(i)));
//            musics.set(indexes.get(i), temp);
//        }
        return musics;
    }

    public Vector<Music> addDateAscending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getAddDate().compareTo(musics.get(j).getAddDate()) < 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public Vector<Music> addDateDescending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getAddDate().compareTo(musics.get(j).getAddDate()) >= 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public Vector<Music> recentlyPlayedDescending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getLastPlayed().compareTo(musics.get(j).getLastPlayed()) > 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
    public Vector<Music> recentlyPlayedAscending()
    {
        for (int i = 0; i < musics.size(); i++)
        {
            for (int j = i + 1; j < musics.size(); j++)
            {
                if (musics.get(i).getLastPlayed().compareTo(musics.get(j).getLastPlayed()) <= 0)
                {
                    Music temp = musics.get(i);
                    musics.set(i, musics.get(j));
                    musics.set(j, temp);
                }
            }
        }
        return musics;
    }
}
