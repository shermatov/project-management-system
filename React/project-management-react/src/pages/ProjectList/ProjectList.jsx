import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';
import { ScrollArea } from '@/components/ui/scroll-area';
import { MixerHorizontalIcon } from '@radix-ui/react-icons';
import React from 'react';

const ProjectList = () => {
  return (
    <>
      <div className="relative px-5 lg:px-0 lg:flex gap-5 justify-center py-5">
        {/* Filter Section */}
        <section className="filterSection">
          <Card className="p-5 sticky top-10">
            <div className="flex justify-between lg:w-[20rem]">
              <p className="text-xl tracking-wider">filter</p>
              <Button variant="ghost" size="icon">
                <MixerHorizontalIcon />
              </Button>
            </div>
            <CardContent className="mt-5">
              <ScrollArea className="space-y-7 h-[70vh]">
              <div>
                <h1 className='pb-3 text-gray-400 border-b'>
                  Category
                </h1>
                <div>
                  
                </div>
              </div>
              </ScrollArea>
            </CardContent>
          </Card>
        </section>

        {/* Project List Section */}
        <section className="projectListSection w-full lg:w-[48rem]">
          
          {/* Add project list items here */}
        </section>
      </div>
    </>
  );
};

export default ProjectList;
